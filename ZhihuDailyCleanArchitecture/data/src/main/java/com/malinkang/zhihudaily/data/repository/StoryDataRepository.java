package com.malinkang.zhihudaily.data.repository;

import com.malinkang.zhihudaily.data.entity.StoryEntity;
import com.malinkang.zhihudaily.data.entity.mapper.StoryEntityDataMapper;
import com.malinkang.zhihudaily.data.repository.datasource.StoryLocalDataStore;
import com.malinkang.zhihudaily.data.repository.datasource.StoryRemoteDataSource;
import com.malinkang.zhihudaily.domain.Story;
import com.malinkang.zhihudaily.domain.repository.StoryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

@Singleton
public class StoryDataRepository implements StoryRepository {

    private  StoryRemoteDataSource mStoryRemoteDataSource;

    private StoryLocalDataStore mStoryLocalDataStore;

    private StoryEntityDataMapper mStoryEntityDataMapper;

    @Inject
    public StoryDataRepository(StoryRemoteDataSource storyRemoteDataSource, StoryLocalDataStore storyLocalDataStore,
                               StoryEntityDataMapper storyEntityDataMapper) {
        this.mStoryRemoteDataSource = storyRemoteDataSource;
        this.mStoryLocalDataStore = storyLocalDataStore;
        this.mStoryEntityDataMapper = storyEntityDataMapper;
    }

    @Override
    public Observable<List<Story>> stories(String date) {
        return mStoryRemoteDataSource.stories(date).flatMap(new Func1<List<StoryEntity>, Observable<List<Story>>>() {
            @Override
            public Observable<List<Story>> call(final List<StoryEntity> storyEntities) {
                return Observable.create(new Observable.OnSubscribe<List<Story>>() {
                    @Override
                    public void call(Subscriber<? super List<Story>> subscriber) {
                        subscriber.onNext(mStoryEntityDataMapper.transform(storyEntities));
                    }
                });
            }
        });
    }

}