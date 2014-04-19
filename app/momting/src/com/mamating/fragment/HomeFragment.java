package com.mamating.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.activity.FollowListActivity;
import com.mamating.activity.TimelineActivity;
import com.mamating.adapter.CourseAdapter;
import com.mamating.bean.Course;
import com.mamating.ui.GridViewNoScroll;
import com.mamating.util.ReadUtil;

/**
 * 首页Fragment
 * 
 * @author malinkang 2014年4月9日
 * 
 */
public class HomeFragment extends BaseFragment {
	@InjectView(R.id.favorite_content)
	GridViewNoScroll favoriteContent;
	@InjectView(R.id.has_study_content)
	GridViewNoScroll hasStudyContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mylib, null);
		ButterKnife.inject(this, view);
		favoriteContent = (GridViewNoScroll) view
				.findViewById(R.id.favorite_content);
		String json = ReadUtil.readFile(getActivity(), "course.json");
		Gson mGson = AppContext.getGson();
		ArrayList<Course> courses = mGson.fromJson(json,
				new TypeToken<List<Course>>() {
				}.getType());
		CourseAdapter adapter = new CourseAdapter(getActivity(), courses);
		favoriteContent.setAdapter(adapter);
		hasStudyContent.setAdapter(adapter);
		return view;
	}

	@OnClick(R.id.recent_listen)
	public void enterTimelineList() {
		startActivity(new Intent(getActivity(), TimelineActivity.class));
	}

	@OnClick({ R.id.follows, R.id.fans })
	public void enterFollowList() {
		startActivity(new Intent(getActivity(), FollowListActivity.class));
	}

}
