package com.malinkang.zhihudaily.internal.di.components;

import android.app.Activity;

import com.malinkang.zhihudaily.internal.di.PerActivity;
import com.malinkang.zhihudaily.internal.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}