package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.malinkang.recyclerview.adapter.ColorAdapterItem;
import com.malinkang.recyclerview.base.BaseActivity;
import com.malinkang.recyclerview.itemdecoration.GridDividerItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by malk on 2017/2/8.
 * http://stackoverflow.com/questions/27083091/recyclerview-inside-scrollview-is-not-working
 */

public class InsideScrollViewActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_inside_scrollview;
    }

    @Override
    public void setupActionBar() {}

    @BindView(R.id.recyclerview1) RecyclerView mRecyclerView1;
    @BindView(R.id.recyclerview2) RecyclerView mRecyclerView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> colors = Arrays.asList(getResources().getStringArray(R.array.colors));
        BaseRecyclerViewAdapter<String> adapter = new BaseRecyclerViewAdapter<String>(this, colors) {
            @Override
            public AdapterItem<String> createAdapterItem() {
                return new ColorAdapterItem();
            }
        };
        mRecyclerView1.setNestedScrollingEnabled(false);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView1.addItemDecoration(new GridDividerItemDecoration(this,10));
        mRecyclerView1.setAdapter(adapter);
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setAdapter(adapter);
    }
}
