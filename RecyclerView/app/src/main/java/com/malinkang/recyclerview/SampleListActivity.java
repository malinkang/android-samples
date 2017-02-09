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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.DemoAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;
import com.malinkang.recyclerview.customlayoutmanager.CustomLayoutManagerSampleListActivity;
import com.malinkang.recyclerview.itemdecoration.DividerItemDecoration;
import com.malinkang.recyclerview.model.Sample;

/**
 * @author malinkang
 */
public class SampleListActivity extends BaseRecyclerViewActivity<Sample> {


    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList.add(new Sample(DragAndSwipeActivity.class,"DragAndSwipeSample"));
        mDataList.add(new Sample(SnapHelperSampleActivity.class,"SnapHelperSample"));
        mDataList.add(new Sample(ItemDecorationSampleActivity.class,"ItemDecorationSample"));
        mDataList.add(new Sample(SortedListActivity.class,"SortedListActivity"));
        mDataList.add(new Sample(DataBindingSample.class,"DataBindingSample"));
        mDataList.add(new Sample(InsideScrollViewActivity.class,"InsideScrollView"));
        mDataList.add(new Sample(CustomLayoutManagerSampleListActivity.class,"CustomLayoutManagerSampleList"));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public AdapterItem<Sample> getAdapterItem() {
        return new DemoAdapterItem();
    }

    @Override
    public void setupActionBar() {}

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        Sample sample = mDataList.get(position);
        Intent intent = new Intent(this,sample.getActivityClass());
        startActivity(intent);
    }
}
