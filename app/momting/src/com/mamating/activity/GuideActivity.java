package com.mamating.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.fragment.GuideFragment;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * 
 * @author malinkang 2014-2-22 下午2:37:16
 * 
 */
public class GuideActivity extends FragmentActivity {

	private final int[] imageIds = { R.drawable.guide_img_01,
			R.drawable.guide_img_02, R.drawable.guide_img_03 };
	@InjectView(R.id.viewpager)
	ViewPager mViewPager;
	@InjectView(R.id.indicator)
	CirclePageIndicator mGuideIndicator;

	private GuidePagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		ButterKnife.inject(this);
		mPagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mPagerAdapter);
		mGuideIndicator.setViewPager(mViewPager);

	}

	private class GuidePagerAdapter extends FragmentPagerAdapter {

		public GuidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			GuideFragment fragment;
			if (position == 2) {
				fragment = GuideFragment.newInstance(imageIds[position], true);
			} else {
				fragment = GuideFragment.newInstance(imageIds[position], false);
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return imageIds.length;
		}

	}

}
