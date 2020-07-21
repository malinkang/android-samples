package com.malinkang.viewpager.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinkang.viewpager.R;

import butterknife.ButterKnife;

/**
 * Created by malinkang on 14/11/2.
 */
public class InnerViewPagerFragment extends Fragment {

    private final String TAG=InnerViewPagerFragment.class.getSimpleName();

//    @BindView(R.id.viewpager) ViewPager mViewpager;

    private String letter;

    public static InnerViewPagerFragment newInstance(String letter) {
        InnerViewPagerFragment fragment = new InnerViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("letter", letter);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letter = getArguments().getString("letter");
        Log.d(TAG,letter+"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my, container, false);
        ButterKnife.bind(this, view);
//        InnerAdapter adapter=new InnerAdapter(getChildFragmentManager(),letter);
//        mViewpager.setAdapter(adapter);
        Log.d(TAG,letter+"onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,letter+"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,letter+"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,letter+"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,letter+"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,letter+"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,letter+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,letter+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,letter+"onDetach");
    }
}
