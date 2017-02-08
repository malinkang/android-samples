package com.malinkang.recyclerview.customlayoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.DemoAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;
import com.malinkang.recyclerview.model.Sample;

/**
 * Created by malk on 2017/2/7.
 */
public class CustomLayoutManagerSampleListActivity extends BaseRecyclerViewActivity<Sample> {

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList.add(new Sample(RhombusLayoutManagerActivity.class,"RhombusLayoutManager"));
        mDataList.add(new Sample(FlowLayoutManagerActivity.class,"FlowLayoutManager"));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public AdapterItem<Sample> getAdapterItem() {
        return new DemoAdapterItem();
    }

    @Override
    public void setupActionBar() {
        setTitle("CustomLayoutManager");
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

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
