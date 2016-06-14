/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.malinkang.zhihudaily.data.entity.mapper;

import com.malinkang.zhihudaily.data.entity.StoryEntity;
import com.malinkang.zhihudaily.domain.Story;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StoryEntityDataMapper {

    @Inject
    public StoryEntityDataMapper() {
    }


    public Story transform(StoryEntity storyEntity) {
        Story story = null;
        if (storyEntity != null) {
            story = new Story();
            story.setGa_prefix(storyEntity.getGa_prefix());
            story.setId(storyEntity.getId());
            story.setImages(storyEntity.getImages());
            story.setTitle(storyEntity.getTitle());
            story.setType(storyEntity.getType());
        }

        return story;
    }


    public List<Story> transform(Collection<StoryEntity> storyEntityCollection) {
        List<Story> userList = new ArrayList<>(20);
        Story story;
        for (StoryEntity storyEntity : storyEntityCollection) {
            story = transform(storyEntity);
            if (story != null) {
                userList.add(story);
            }
        }

        return userList;
    }
}
