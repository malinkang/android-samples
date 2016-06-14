package com.malinkang.zhihudaily.domain.interactor;

import com.malinkang.zhihudaily.domain.executor.PostExecutionThread;
import com.malinkang.zhihudaily.domain.executor.ThreadExecutor;
import com.malinkang.zhihudaily.domain.repository.StoryRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetStories extends UseCase<String> {

    private final StoryRepository mStoryRepository;

    @Inject
    public GetStories(StoryRepository storyRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mStoryRepository = storyRepository;
    }


    @Override
    protected Observable buildUseCaseObservable(String... params) {
        return mStoryRepository.stories(params[0]);
    }
}