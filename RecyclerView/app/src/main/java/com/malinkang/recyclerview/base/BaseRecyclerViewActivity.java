package com.malinkang.recyclerview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.malinkang.recyclerview.R;
import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by malk on 16/11/4.
 */
public abstract class BaseRecyclerViewActivity<T> extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview) protected RecyclerView mRecyclerView;
    protected List<T> mDataList;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseRecyclerViewAdapter<T> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList = new ArrayList<>();
        setupRecyclerView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    public void setupRecyclerView() {
        mLayoutManager = getLayoutManager();
        mAdapter = new BaseRecyclerViewAdapter<T>(this, mDataList, mLayoutManager, mRecyclerView, isDrag()) {
            @Override
            public AdapterItem<T> createAdapterItem() {
                return getAdapterItem();
            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract AdapterItem<T> getAdapterItem();

    public boolean isDrag() {
        return false;
    }

    @Override
    public void onItemClick(View v, int position) {
    }
}
