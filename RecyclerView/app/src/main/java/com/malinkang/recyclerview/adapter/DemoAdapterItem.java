package com.malinkang.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.malinkang.recyclerview.model.Sample;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malk on 16/11/4.
 */

public class DemoAdapterItem implements AdapterItem<Sample> {
    @BindView(android.R.id.text1)TextView mTextView;
    @Override
    public int getLayoutResId(int viewType) {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public void bindViews(View root) {
        ButterKnife.bind(this,root);
    }

    @Override
    public void bindData(Context context, int position, Sample sample, int viewType) {
        mTextView.setText(sample.getTitle());
    }
}
