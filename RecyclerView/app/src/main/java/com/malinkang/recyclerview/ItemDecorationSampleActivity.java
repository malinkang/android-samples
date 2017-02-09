package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.ColorAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;
import com.malinkang.recyclerview.itemdecoration.GridDividerItemDecoration;

/**
 * Created by malk on 16/11/7.
 */

public class ItemDecorationSampleActivity extends BaseRecyclerViewActivity<String> {

    String[] colors = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colors = getResources().getStringArray(R.array.colors);
        for (int i = 0; i < colors.length; i++) {
            mDataList.add(colors[i]);
        }
    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(this,20));
    }



    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this,3);
    }

    @Override
    public AdapterItem<String> getAdapterItem() {
        return new ColorAdapterItem();
    }

    @Override
    public void setupActionBar() {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("ItemDecorationSample");
    }

}
