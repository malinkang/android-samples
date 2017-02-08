package com.malinkang.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.malinkang.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malk on 16/11/7.
 */

public class ColorAdapterItem implements AdapterItem<String> {
    @BindView(R.id.view) View view;

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_color;
    }

    @Override
    public void bindViews(View root) {
        ButterKnife.bind(this,root);
    }

    @Override
    public void bindData(Context context, int position, String color, int viewType) {
        view.setBackgroundColor(Color.parseColor(color));
    }
}
