package com.mamating.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.ui.GridViewNoScroll;

/**
 * 首页Fragment
 * 
 * @author malinkang 2014年4月9日
 * 
 */
public class HomeFragment extends BaseFragment {
	private static final String[] CONTENT = new String[] { "已学课程", "想学课程", "帖子" };

	@InjectView(R.id.favorite_content)
	GridViewNoScroll favoriteContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mylib, null);
		ButterKnife.inject(this, view);

		return view;
	}

}
