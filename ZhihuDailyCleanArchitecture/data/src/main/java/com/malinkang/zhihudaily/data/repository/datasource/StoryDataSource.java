package com.malinkang.zhihudaily.data.repository.datasource;

import com.malinkang.zhihudaily.data.entity.StoryEntity;

import java.util.List;

import rx.Observable;

public interface StoryDataSource {
    Observable<List<StoryEntity>> stories(String date);
}
