package com.malinkang.zhihudaily.internal.di.modules;

import android.content.Context;

import com.malinkang.zhihudaily.Application;
import com.malinkang.zhihudaily.UIThread;
import com.malinkang.zhihudaily.data.executor.JobExecutor;
import com.malinkang.zhihudaily.data.repository.StoryDataRepository;
import com.malinkang.zhihudaily.domain.executor.PostExecutionThread;
import com.malinkang.zhihudaily.domain.executor.ThreadExecutor;
import com.malinkang.zhihudaily.domain.repository.StoryRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    StoryRepository provideStoryRepository(StoryDataRepository userDataRepository) {
        return userDataRepository;
    }
}