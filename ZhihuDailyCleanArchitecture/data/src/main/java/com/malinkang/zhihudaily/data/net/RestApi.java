package com.malinkang.zhihudaily.data.net;

import com.malinkang.zhihudaily.data.entity.StoryListEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RestApi {
    @GET("news/before/{date}")
    Observable<StoryListEntity> getStories(@Path("date") String date);
}