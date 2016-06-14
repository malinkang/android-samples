package com.ldzs.liveforever;

import android.content.Context;

import java.util.concurrent.Executors;

/**
 * Created by ldzs_android_1 on 16/6/13.
 */

public class LiveManager {
    public void initService(final Context context) {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //守护进程服务
                String serviceName = NotificationService.class.getSimpleName();
                String executable = "libhelper.so";
                String aliasfile = "helper";
                NativeRuntime.getInstance().RunExecutable(context.getPackageName(), executable, aliasfile, context.getPackageName() + "/" + serviceName);
            }
        });
    }
}
