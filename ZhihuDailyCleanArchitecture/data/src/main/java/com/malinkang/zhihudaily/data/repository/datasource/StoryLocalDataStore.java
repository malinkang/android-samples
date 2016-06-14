package com.malinkang.zhihudaily.data.repository.datasource;

import com.malinkang.zhihudaily.data.entity.StoryEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class StoryLocalDataStore implements StoryDataSource {
    @Inject
    public StoryLocalDataStore() {

    }

    @Override
    public Observable<List<StoryEntity>> stories(String date) {
        return null;
    }


}