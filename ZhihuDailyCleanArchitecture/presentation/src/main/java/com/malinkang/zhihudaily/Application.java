package com.malinkang.zhihudaily;

import com.malinkang.zhihudaily.internal.di.components.ApplicationComponent;
import com.malinkang.zhihudaily.internal.di.components.DaggerApplicationComponent;
import com.malinkang.zhihudaily.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ldzs_android_1 on 16/6/12.
 */

public class Application extends android.app.Application {
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
