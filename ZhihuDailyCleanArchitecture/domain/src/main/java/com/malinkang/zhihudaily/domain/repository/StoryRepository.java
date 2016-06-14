package com.malinkang.zhihudaily.domain.repository;

import com.malinkang.zhihudaily.domain.Story;

import java.util.List;

import rx.Observable;

public interface StoryRepository {
    //获取story列表
    Observable<List<Story>> stories(String date);
}