package com.malinkang.viewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    public static final String TAG = TestFragment.class.getSimpleName();

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
        Log.d(TAG,letter+", onCreate ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,letter+", onCreateView ");

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        mTextview.setText(letter);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG,letter+", setUserVisibleHint ");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,letter+", onStart ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,letter+", onResume ");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,letter+", onPause ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,letter+", onStop ");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,letter+", onDestroyView ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,letter+", onDestroy ");

    }


}
