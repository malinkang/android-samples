package com.malinkang.jnisample;

/**
 * Created by ldzs_android_1 on 16/6/13.
 */

public class JniUtil {
    static {
        System.loadLibrary("malinkang");    //defaultConfig.ndk.moduleName
    }

    public native String sayHi();
}
