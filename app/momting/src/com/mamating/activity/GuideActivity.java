package com.mamating.activity;

import android.content.Context;
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
import com.mamating.ui.GuideIndicator;

/**
 * 
 * @author malinkang 2014-2-22 下午2:37:16
 * 
 */
public class GuideActivity extends FragmentActivity {

	private final int[] imageIds = { R.drawable.guide_img_01,
			R.drawable.guide_img_02, R.drawable.guide_img_03 };
	private Context mContext;
	@InjectView(R.id.viewpager)
	ViewPager mViewPager;
	@InjectView(R.id.indicator)
	GuideIndicator mGuideIndicator;

	private GuidePagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_guide);
		ButterKnife.inject(this);

		mPagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mPagerAdapter);
		mGuideIndicator.setPageCount(imageIds.length);
		mGuideIndicator
				.setOnPageSelectedListener(new GuideIndicator.OnPageSelectedListener() {
					@Override
					public void onPageStripSelected(int position) {
						position = Math.min(mPagerAdapter.getCount() - 1,
								position);
						if (mViewPager.getCurrentItem() != position) {
							mViewPager.setCurrentItem(position);
						}
					}
				});

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						mGuideIndicator.setCurrentPage(position);
					}
				});

	}

	private class GuidePagerAdapter extends FragmentPagerAdapter {

		public GuidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			GuideFragment fragment = GuideFragment
					.newInstance(imageIds[position]);
			return fragment;
		}

		@Override
		public int getCount() {
			return imageIds.length;
		}

	}

}
