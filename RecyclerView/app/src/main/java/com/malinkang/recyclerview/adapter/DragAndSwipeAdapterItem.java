package com.malinkang.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.malinkang.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malk on 16/11/4.
 */

public class DragAndSwipeAdapterItem implements AdapterItem<Integer> {
    @BindView(R.id.text) TextView text;

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_main;
    }

    @Override
    public void bindViews(View root) {
        ButterKnife.bind(this, root);
    }

    @Override
    public void bindData(Context context, int position, Integer integer, int viewType) {
        text.setText(integer+"");
    }
}
