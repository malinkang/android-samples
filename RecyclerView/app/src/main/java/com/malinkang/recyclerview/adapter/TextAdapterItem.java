package com.malinkang.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.malinkang.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malk on 2017/2/7.
 */

public class TextAdapterItem implements AdapterItem<String> {

    @BindView(R.id.text) TextView  textView;

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_text;
    }

    @Override
    public void bindViews(View root) {
        ButterKnife.bind(this,root);
    }

    @Override
    public void bindData(Context context, int position, String s, int viewType) {
        textView.setText(s);
    }
}
