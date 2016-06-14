package com.malinkang.zhihudaily.internal.di.components;

import android.content.Context;

import com.malinkang.zhihudaily.domain.executor.PostExecutionThread;
import com.malinkang.zhihudaily.domain.executor.ThreadExecutor;
import com.malinkang.zhihudaily.domain.repository.StoryRepository;
import com.malinkang.zhihudaily.internal.di.modules.ApplicationModule;
import com.malinkang.zhihudaily.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    StoryRepository storyRepository();
}