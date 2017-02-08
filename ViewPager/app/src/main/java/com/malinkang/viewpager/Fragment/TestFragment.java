package com.malinkang.viewpager.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malinkang.viewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malinkang on 14/11/2.
 */
public class TestFragment extends Fragment {

    @BindView(R.id.textview) TextView mTextview;
    private String letter;

    public static TestFragment newInstance(String letter) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("letter", letter);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letter = getArguments().getString("letter");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        mTextview.setText(letter);
        return view;
    }
}
