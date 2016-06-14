package com.malinkang.zhihudaily.internal.di.modules;

import com.malinkang.zhihudaily.domain.interactor.GetStories;
import com.malinkang.zhihudaily.domain.interactor.UseCase;
import com.malinkang.zhihudaily.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryModule {


  @Provides
  @PerActivity
  @Named("userList")
  UseCase<String> provideGetStories(GetStories getStories) {
    return getStories;
  }
}