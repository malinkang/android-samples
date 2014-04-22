package com.mamating.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.activeandroid.content.ContentProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.activity.AddCourseActivity;
import com.mamating.activity.FollowerListActivity;
import com.mamating.activity.FollowingListActivity;
import com.mamating.activity.TimelineActivity;
import com.mamating.adapter.CourseAdapter;
import com.mamating.api.FriendShipApi;
import com.mamating.bean.Count;
import com.mamating.bean.CountInfo;
import com.mamating.bean.Course;
import com.mamating.constants.CountInfoTable;
import com.mamating.ui.CircleImageView;
import com.mamating.ui.GridViewNoScroll;
import com.mamating.util.ReadUtil;

/**
 * 首页Fragment
 * 
 * @author malinkang 2014年4月9日
 * 
 */
public class HomeFragment extends BaseFragment implements
		LoaderCallbacks<Cursor> {
	@InjectView(R.id.iv_avatar)
	CircleImageView avatar;
	@InjectView(R.id.favorite_content)
	GridViewNoScroll favoriteContent;
	@InjectView(R.id.has_study_content)
	GridViewNoScroll hasStudyContent;
	private FriendShipApi frinFriendShipApi;
	@InjectView(R.id.follows_count)
	TextView followsCount;
	@InjectView(R.id.fans_count)
	TextView fansCount;
	@InjectView(R.id.feed_count)
	TextView feedCount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mylib, null);
		ButterKnife.inject(this, view);
		frinFriendShipApi = new FriendShipApi(getActivity());
		frinFriendShipApi.getFollowCount(account.getUid());
		getLoaderManager().initLoader(0, null, this);
		// 显示头像
		mImageLoader.displayImage(account.getAvatar(), avatar);

		String json = ReadUtil.readFile(getActivity(), "course.json");
		Gson mGson = AppContext.getGson();
		ArrayList<Course> courses = mGson.fromJson(json,
				new TypeToken<List<Course>>() {
				}.getType());

		CourseAdapter adapter = new CourseAdapter(getActivity(), courses, false);
		favoriteContent.setAdapter(adapter);
		hasStudyContent.setAdapter(adapter);
		return view;
	}

	@OnClick(R.id.recent_listen)
	public void enterTimelineList() {
		startActivity(new Intent(getActivity(), TimelineActivity.class));
	}

	@OnClick(R.id.follows)
	public void enterFollowingList() {

		startActivity(new Intent(getActivity(), FollowingListActivity.class));
	}

	@OnClick(R.id.fans)
	public void enterFollowerList() {
		startActivity(new Intent(getActivity(), FollowerListActivity.class));
	}

	@OnClick(R.id.create_collect)
	public void addCourse() {
		Intent intent = new Intent(getActivity(), AddCourseActivity.class);
		intent.putExtra("b", true);
		startActivity(intent);
	}

	@OnClick({ R.id.show_all_mycollect, R.id.show_all_favorite })
	public void showAllCourse() {
		Intent intent = new Intent(getActivity(), AddCourseActivity.class);
		intent.putExtra("b", false);
		startActivity(intent);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(getActivity(), ContentProvider.createUri(
				CountInfo.class, null), null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		if (cursor != null && cursor.moveToNext()) {
			String json = cursor.getString(cursor
					.getColumnIndex(CountInfoTable.COLUMN_COUNT_JSON));
			Count count = mGson.fromJson(json, Count.class);
			followsCount.setText(count.getFollowing_count() + "");
			fansCount.setText(count.getFollower_count() + "");
			feedCount.setText(count.getFeed_count() + "");
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {

	}
}
