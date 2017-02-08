package com.malinkang.animationsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ldzs_android_1 on 16/5/19.
 */
public class PagerFragment extends Fragment {

    private int mResId;

    public static PagerFragment newInstance(int resId) {

        Bundle args = new Bundle();
        args.putInt("resId",resId);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResId = getArguments().getInt("resId");
    }

    @BindView(R.id.iv)
    ImageView mImageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pager,container,false);
        ButterKnife.bind(this,view);
        mImageView.setImageResource(mResId);
        return view;
    }
}
