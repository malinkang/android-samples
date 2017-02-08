package com.malinkang.recyclerview.customlayoutmanager;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.malinkang.recyclerview.adapter.AdapterItem;
import com.malinkang.recyclerview.adapter.TextAdapterItem;
import com.malinkang.recyclerview.base.BaseRecyclerViewActivity;
import com.malinkang.recyclerview.customlayoutmanager.layoutmanager.FlowLayoutManager;

/**
 * Created by malk on 2017/2/7.
 */

public class FlowLayoutManagerActivity extends BaseRecyclerViewActivity<String> {
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        // 限制每行最多3个
        //flowLayoutManager.maxItemsPerLine(3);
        return flowLayoutManager;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList.add("蓝瘦,香菇");
        mDataList.add("老司机带带我");
        mDataList.add("一言不合就飙车");
        mDataList.add("洪荒之力");
        mDataList.add("狗带");
        mDataList.add("友谊的小船说翻就翻");
        mDataList.add("撩妹");
        mDataList.add("都是套路");
        mDataList.add("我和我的小伙伴们都惊呆了");
        mDataList.add("葛优躺");
        mDataList.add("感觉身体被掏空");
        mDataList.add("伤不起");
        mDataList.add("活久见");
        mDataList.add("吃瓜群众");
        mDataList.add("不作死就不会死");
        mDataList.add("带我装逼带我飞");
        mDataList.add("辣眼睛");
        mDataList.add("厉害了我的哥");
        mDataList.add("你是猴子请来的逗逼吗");
        mDataList.add("然并卵");
        mDataList.add("你咋不上天呢");
        mDataList.add("日了狗了");
        mDataList.add("妈的智障");
        mDataList.add("我保证不打死你");
        mDataList.add("我也是醉了");
        mDataList.add("捡肥皂");
        mDataList.add("妖艳贱货");
        mDataList.add("重要的事情说三遍");
        mDataList.add("一脸懵逼");
        mDataList.add("总有刁民想害朕");
        mDataList.add("有钱任性");
        mDataList.add("土豪");
        mDataList.add("战五渣");
        mDataList.add("意大利炮");
    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(8, 8, 8, 8);
            }
        });
    }

    @Override
    public AdapterItem<String> getAdapterItem() {
        return new TextAdapterItem();
    }

    @Override
    public void setupActionBar() {
        setTitle("FlowLayoutManager");
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        Toast.makeText(this, mDataList.get(position), Toast.LENGTH_SHORT).show();
    }
}
