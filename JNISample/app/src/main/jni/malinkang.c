//
// Created by ldzs_android_1 on 16/6/13.
//

#include "com_malinkang_jnisample_JniUtil.h"

JNIEXPORT jstring JNICALL Java_com_malinkang_jnisample_JniUtil_sayHi
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "This just a test for Android Studio NDK JNI developer!");
}