package com.malinkang.zhihudaily;

import com.malinkang.zhihudaily.model.StoryModel;

import java.util.List;

public interface StoryListView extends LoadDataView {

    void renderStoryList(List<StoryModel> storyModelList);


    void viewStory(StoryModel storyModel);
}