package com.malinkang.zhihudaily.data.entity;

import java.util.List;

/**
 * Created by ldzs_android_1 on 16/6/12.
 */

public class StoryListEntity {
    private String date;

    private List<StoryEntity> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoryEntity> stories) {
        this.stories = stories;
    }


}
