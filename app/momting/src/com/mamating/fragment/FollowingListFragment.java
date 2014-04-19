package com.mamating.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamating.R;

/**
 * 关注人的Fragment
 * 
 * @author malinkang 2014年4月9日
 * 
 */
public class FollowingListFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_list, null);
		return view;
	}
}