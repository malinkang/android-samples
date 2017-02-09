/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.DragAndSwipeAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;


/**
 * @author malinkang
 *
 */
public class DragAndSwipeActivity extends BaseRecyclerViewActivity<Integer> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i= 0; i<10 ; i++) {
            mDataList.add(i);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public AdapterItem<Integer> getAdapterItem() {
        return new DragAndSwipeAdapterItem();
    }

    @Override
    public boolean isDrag() {
        return true;
    }

    @Override
    public void setupActionBar() {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("DragAndSwipe");
    }
}
