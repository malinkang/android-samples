package com.mamating.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;

/**
 * 通知Fragment
 * 
 * @author malinkang 2014年4月9日
 * 
 */
public class NotificationFragment extends BaseFragment {

	@InjectView(R.id.viewpager)
	ViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_notification, null);
		ButterKnife.inject(this, view);
		NotificationPagerAdapter adapter = new NotificationPagerAdapter(
				getFragmentManager());
		mViewPager.setAdapter(adapter);
		// indicator.setViewPager(mViewPager);
		// indicator.setIndicatorColor(Color.parseColor("#F49AC0"));
		return view;
	}

	private class NotificationPagerAdapter extends FragmentPagerAdapter {
		private final String[] TITLES = { "通知", "关注", "粉丝" };

		public NotificationPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new HomeFragment();
			case 1:
				return new FollowingListFragment();
			case 2:
				return new UserCenterFragment();
			}
			return null;
		}

		@Override
		public int getCount() {

			return TITLES.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}
	}
}