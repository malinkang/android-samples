package com.malinkang.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.internal.impldep.org.apache.commons.codec.digest.DigestUtils
import org.gradle.internal.impldep.org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
class MyTransform extends Transform {

    @Override
    String getName() {
        return "MyTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println("transform start...")
        def startTime = System.currentTimeMillis()
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        //删除之前的输出
        if (outputProvider != null)
            outputProvider.deleteAll()
        inputs.each {
            TransformInput input ->
                input.directoryInputs.each { DirectoryInput directoryInput ->
                    print("directoryInput = $directoryInput")
                }
                input.jarInputs.each {
                    JarInput jarInput -> handleJar(jarInput,outputProvider)
                }
        }
        def endTime = System.currentTimeMillis()
        println("cost ${endTime - startTime} ms")
    }
    static void handleJar(JarInput jarInput,TransformOutputProvider outputProvider){
        if(jarInput.file.absolutePath.endsWith(".jar")){
            def jarName = jarInput.name
            println("jarName = $jarName")
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4)
            }
            JarFile jarFile = new JarFile(jarInput.file)
            Enumeration enumeration = jarFile.entries()
            File tmpFile = new File(jarInput.file.getParent() + File.separator + "classes_temp.jar")
            if (tmpFile.exists()) {
                tmpFile.delete()
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tmpFile))
            while(enumeration.hasMoreElements()){
                JarEntry jarEntry = enumeration.nextElement()
                String entryName= jarEntry.name
                print("jarEntry = $entryName")

                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream is = jarFile.getInputStream(zipEntry)
                if(checkClassFile(entryName)){
                    jarOutputStream.putNextEntry(zipEntry)
                }
            }
        }
    }
    static boolean checkClassFile(String name){
        return name.endsWith(".class") && !name.startsWith("R\$")
        && "R.class"!=name &&"BuildConfig.class"!=name
    }
}