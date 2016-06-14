package com.malinkang.zhihudaily.internal.di.components;

import com.malinkang.zhihudaily.internal.di.PerActivity;
import com.malinkang.zhihudaily.internal.di.modules.ActivityModule;
import com.malinkang.zhihudaily.internal.di.modules.StoryModule;
import com.malinkang.zhihudaily.view.activity.MainActivity;
import com.malinkang.zhihudaily.view.adapter.MainAdapter;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, StoryModule.class})
public interface StoryComponent extends ActivityComponent {
    void inject(MainActivity activity);
}