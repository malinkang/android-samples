/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <string.h>
#include <jni.h>

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <android/log.h>
#include <unistd.h>
#include <sys/inotify.h>

/* 宏定义begin */
//清0宏
#define MEM_ZERO(pDest, destSize) memset(pDest, 0, destSize)

//LOG宏定义
#define LOG_INFO(tag, msg) __android_log_write(ANDROID_LOG_INFO, tag, msg)
#define LOG_DEBUG(tag, msg) __android_log_write(ANDROID_LOG_DEBUG, tag, msg)
#define LOG_WARN(tag, msg) __android_log_write(ANDROID_LOG_WARN, tag, msg)
#define LOG_ERROR(tag, msg) __android_log_write(ANDROID_LOG_ERROR, tag, msg)

/* 内全局变量begin */
static char c_TAG[] = "onEvent";
static jboolean b_IS_COPY = JNI_TRUE;

jstring Java_com_weishang_wxrd_natives_UninstallObserver_startWork(JNIEnv* env,
		jobject thiz, jstring path, jstring url, jint version) {
	jstring tag = (*env)->NewStringUTF(env, c_TAG);

	//初始化log
	LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
			(*env)->GetStringUTFChars(env, (*env)->NewStringUTF(env, "init OK"),
					&b_IS_COPY));

	//fork子进程，以执行轮询任务
	pid_t pid = fork();
	if (pid < 0) {
		//出错log
		LOG_ERROR((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env,
						(*env)->NewStringUTF(env, "fork failed !!!"),
						&b_IS_COPY));
	} else if (pid == 0) {
		//子进程注册目录监听器
		int fileDescriptor = inotify_init();
		if (fileDescriptor < 0) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env,
							(*env)->NewStringUTF(env,
									"inotify_init failed !!!"), &b_IS_COPY));

			exit(1);
		}

		int watchDescriptor;

		watchDescriptor = inotify_add_watch(fileDescriptor,
				(*env)->GetStringUTFChars(env, path, NULL), IN_DELETE);
		if (watchDescriptor < 0) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env,
							(*env)->NewStringUTF(env,
									"inotify_add_watch failed !!!"),
							&b_IS_COPY));

			exit(1);
		}

		//分配缓存，以便读取event，缓存大小=一个struct inotify_event的大小，这样一次处理一个event
		void *p_buf = malloc(sizeof(struct inotify_event));
		if (p_buf == NULL) {
			LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
					(*env)->GetStringUTFChars(env,
							(*env)->NewStringUTF(env, "malloc failed !!!"),
							&b_IS_COPY));

			exit(1);
		}
		//开始监听
		LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env,
						(*env)->NewStringUTF(env, "start observer"),
						&b_IS_COPY));
		//read会阻塞进程，
		size_t readBytes = read(fileDescriptor, p_buf,
				sizeof(struct inotify_event));

		//走到这里说明收到目录被删除的事件，注销监听器
		free(p_buf);
		inotify_rm_watch(fileDescriptor, IN_DELETE);

		//目录不存在log
		LOG_DEBUG((*env)->GetStringUTFChars(env, tag, &b_IS_COPY),
				(*env)->GetStringUTFChars(env,
						(*env)->NewStringUTF(env, "uninstalled"), &b_IS_COPY));

		if (version >= 17) {
			//4.2以上的系统由于用户权限管理更严格，需要加上 --user 0
			execlp("am", "am", "start", "--user", "0", "-a",
					"android.intent.action.VIEW", "-d",
					(*env)->GetStringUTFChars(env, url, NULL), (char *) NULL);
		} else {
			execlp("am", "am", "start", "-a", "android.intent.action.VIEW",
					"-d", (*env)->GetStringUTFChars(env, url, NULL),
					(char *) NULL);
		}

		//扩展：可以执行其他shell命令，am(即activity manager)，可以打开某程序、服务，broadcast intent，等等

	} else {
		//父进程直接退出，使子进程被init进程领养，以避免子进程僵死
	}

	return (*env)->NewStringUTF(env, "Hello from JNI !");
}

/*
 * Class:     com.weishang.wxrd.natives.UninstallObserver
 * Method:    sing
 * Signature: (Landroid/content/Context;)Ljava/lang/String;
 */
jstring Java_com_weishang_wxrd_natives_UninstallObserver_sing(JNIEnv * env, jobject ob1, jobject thiz){

	jclass thisClass=(*env)->GetObjectClass(env,thiz);
	jmethodID getPNId=(*env)->GetMethodID(env,thisClass,"getPackageName","()Ljava/lang/String;");
	jstring packName=(jstring)(*env)->CallObjectMethod(env,thiz,getPNId);


	jmethodID getPMId=(*env)->GetMethodID(env,thisClass,"getPackageManager","()Landroid/content/pm/PackageManager;");
	jobject packManger=(*env)->CallObjectMethod(env,thiz,getPMId);

	jmethodID getPIId=(*env)->GetMethodID(env,(*env)->GetObjectClass(env,packManger),"getPackageInfo","(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
	jobject packageInfo=(*env)->CallObjectMethod(env,packManger,getPIId,packName,0x00000040);


	jfieldID signaturesId=(*env)->GetFieldID(env,(*env)->GetObjectClass(env,packageInfo),"signatures","[Landroid/content/pm/Signature;");
	jobject signatures=(*env)->GetObjectField(env,packageInfo,signaturesId);
	jobjectArray signaturesArray=(jobjectArray)signatures;

	//signature=signs[0]
	jobject signature=(*env)->GetObjectArrayElement(env,signaturesArray,0);
	jmethodID signatureBytesMId=(*env)->GetMethodID(env,(*env)->GetObjectClass(env,signature),"toByteArray","()[B");
	//sign.toByteArray()
	jobject signatureBytes=(*env)->CallObjectMethod(env,signature,signatureBytesMId);
///////////////// public void parseSignature(byte[] signature) {}/////////////////////////////////////////////////////////////////

	jclass certificateFactoryClass=(*env)->FindClass(env,"java/security/cert/CertificateFactory");
	jmethodID getInstanceMId=(*env)->GetStaticMethodID(env,certificateFactoryClass,"getInstance","(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;");
	jobject certFactory=(*env)->CallStaticObjectMethod(env,certificateFactoryClass,getInstanceMId,(*env)->NewStringUTF(env,"X.509"));
///////////////////////////////////
	jclass byteInStreamClass=(*env)->FindClass(env,"java/io/ByteArrayInputStream");
	jmethodID byteArrayInStreamConMId= (*env)->GetMethodID(env,byteInStreamClass,"<init>","([B)V");
	jobject byteArrayInStream=(*env)->NewObject(env,byteInStreamClass,byteArrayInStreamConMId,signatureBytes);

	jmethodID generateCertificateMId=(*env)->GetMethodID(env,certificateFactoryClass,"generateCertificate","(Ljava/io/InputStream;)Ljava/security/cert/Certificate;");
	jobject certificate=(*env)->CallObjectMethod(env,certFactory,generateCertificateMId,byteArrayInStream);


	jmethodID getPKMId=(*env)->GetMethodID(env,(*env)->GetObjectClass(env,certificate),"getPublicKey","()Ljava/security/PublicKey;");

	jobject publicKey=(*env)->CallObjectMethod(env,certificate,getPKMId);
	jmethodID toStringMId=(*env)->GetMethodID(env,(*env)->GetObjectClass(env,publicKey),"toString","()Ljava/lang/String;");
	jstring publickeyStr=(jstring)(*env)->CallObjectMethod(env,publicKey,toStringMId);
///////////////////////
	jmethodID containsMId=(*env)->GetMethodID(env,(*env)->GetObjectClass(env,publickeyStr),"contains","(Ljava/lang/CharSequence;)Z");
	jboolean isCorrect=(*env)->CallBooleanMethod(env,publickeyStr,containsMId,(*env)->NewStringUTF(env,"a50542f3a2805a0e472c91ad65c621f33ffeab09419e5eb8e9f86785989b4216e024b8ef6d17a2444c1b209dd2c7fec4dc07165766a8e09c57432d9471fb2a881084ee9fd6ebe1089c0b77ffade8c421c2c20e1962cc084803720eaf32ca0087f3916b811f6f1e945223eca9488e40a0910b52fbc219a063ed00151460aa33f47c4d96be151146fa5abb9c774e294c45a55c45074445ac38ba25a1784e98d99ff52fe61098c12750543d5d80bbe9578bb0a201f9290cadd03e157a1b0900b76ed7496ec6bd1a7e85f7f7133e08a537c8576963d9cd7feab38201a0fc48a0c59da74c8ab2dcae2612ed9ad39c319f45e1d2f81b230d45360628c32cfbb533de63"));
	if(!isCorrect)
		return (*env)->NewStringUTF(env,"wrong");

	jstring jstr=(*env)->NewStringUTF(env,"correct");
		return jstr;

}
