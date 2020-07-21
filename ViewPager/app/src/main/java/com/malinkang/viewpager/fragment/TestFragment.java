package com.malinkang.viewpager.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.malinkang.viewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by malinkang on 14/11/2.
 */
public class TestFragment extends Fragment {

    public static final String TAG = TestFragment.class.getSimpleName();

    @BindView(R.id.textview) TextView mTextview;
    private char letter;

    public static TestFragment newInstance(Character letter) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putChar("letter", letter);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, letter+", onSaveInstanceState :");
        outState.putChar("letter", letter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            letter = savedInstanceState.getChar("letter");
            Log.d(TAG, letter+", onCreate :");

        } else {
            letter = getArguments().getChar("letter");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,letter+", onCreateView ");

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        mTextview.setText(String.valueOf(letter));

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
