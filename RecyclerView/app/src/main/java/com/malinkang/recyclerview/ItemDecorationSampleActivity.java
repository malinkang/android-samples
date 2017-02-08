package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.ColorAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;

import java.util.Random;

import butterknife.OnClick;

/**
 * Created by malk on 16/11/7.
 */

public class ItemDecorationSampleActivity extends BaseRecyclerViewActivity<String> {

    String[] colors = null;

    View mFooterView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.item_footer, null, false);
        colors = getResources().getStringArray(R.array.colors);

    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        //mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(10));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) mLayoutManager) {
//            @Override
//            public void onLoadMore() {
//                if (!mAdapter.hasFooter()) {
//                    mAdapter.addFooter(mFooterView);
//                }
//
//                new Handler().postDelayed(() -> {
//                    for (int i = 0; i < colors.length; i++) {
//                        mDataList.add(colors[i]);
//                    }
//                    if (mAdapter.hasFooter()) {
//                        mAdapter.removeFooter(mFooterView);
//                    }
//                    mAdapter.notifyDataSetChanged();
//                }, 1000);
//
//            }
//        });
//        ((GridLayoutManager) mLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 0) {
//                    return 1;
//                } else if (mAdapter.isFooter(position)) {
//                    return 4;
//                } else {
//                    return 1;
//                }
//            }
//        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_grid;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
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

    @OnClick(R.id.add)
    public void add() {
        int i = new Random().nextInt(colors.length - 1);
        Log.d(ItemDecorationSampleActivity.class.getSimpleName(), "i=" + i);
        mDataList.add(colors[i]);
        mAdapter.notifyItemInserted(mDataList.size() - 1);
    }

    @OnClick(R.id.delete)
    public void delete() {
        if (mDataList.size() > 0) {
            mDataList.remove(mDataList.size() - 1);
            mAdapter.notifyItemRemoved(mDataList.size());
        }
    }
}
