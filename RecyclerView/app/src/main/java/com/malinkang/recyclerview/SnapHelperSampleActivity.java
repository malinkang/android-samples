package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.ColorAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;

/**
 * Created by malk on 16/11/7.
 */

public class SnapHelperSampleActivity extends BaseRecyclerViewActivity<String> {

    @Override
    public int getLayout() {
        return R.layout.activity_snaphelper;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] colors = getResources().getStringArray(R.array.colors);
        for (int i = 0; i < 2 ;i++) {
            mDataList.add(colors[i]);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public AdapterItem<String> getAdapterItem() {
        return new ColorAdapterItem();
    }

    @Override
    public void setupActionBar() {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("SnapHelperSample");
    }


}
