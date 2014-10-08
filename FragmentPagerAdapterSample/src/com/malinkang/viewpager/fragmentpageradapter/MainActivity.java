package com.malinkang.viewpager.fragmentpageradapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.malinkang.fragmentpageradaptersample.R;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	private ViewPager mViewPager;

	private boolean isScrolling;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(5);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

		/***
		 * positionOffset 当前页面偏移的百分比
		 * 
		 * positionOffsetPixels 当前页面偏移的像素
		 * 
		 */

		Log.d(TAG, "position---" + position + "positionOffset---"
				+ positionOffset + "positionOffsetPixels---"
				+ positionOffsetPixels);
		if (isScrolling) {
			Log.d(TAG, "正在滑动");
		}
	}

	@Override
	public void onPageSelected(int position) {
		Log.d(TAG, "position---" + position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

		Log.d(TAG, "state---" + state);

		if (state == 1) {
			isScrolling = true;
		} else {
			isScrolling = false;
		}

	}
}
