package com.malinkang.zhihudaily.mapper;

import com.malinkang.zhihudaily.domain.Story;
import com.malinkang.zhihudaily.internal.di.PerActivity;
import com.malinkang.zhihudaily.model.StoryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class StoryModelDataMapper {

    @Inject
    public StoryModelDataMapper() {
    }

    public StoryModel transform(Story story) {
        if (story == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        StoryModel storyModel = new StoryModel();
        storyModel.setType(story.getType());
        storyModel.setTitle(story.getTitle());
        storyModel.setImages(story.getImages());
        storyModel.setGa_prefix(storyModel.getGa_prefix());

        return storyModel;
    }

    public List<StoryModel> transform(List<Story> storyCollection) {
        List<StoryModel> storyModelCollection;

        if (storyCollection != null && !storyCollection.isEmpty()) {
            storyModelCollection = new ArrayList<>();
            for (Story user : storyCollection) {
                storyModelCollection.add(transform(user));
            }
        } else {
            storyModelCollection = Collections.emptyList();
        }

        return storyModelCollection;
    }
}