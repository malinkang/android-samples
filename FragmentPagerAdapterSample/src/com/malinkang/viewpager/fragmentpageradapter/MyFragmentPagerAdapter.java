package com.malinkang.viewpager.fragmentpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private final static String[] LETTERS = { "A", "B", "C", "D", "E", "F" };

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return TestFragment.newInstance(LETTERS[position]);
	}

	@Override
	public int getCount() {
		return LETTERS.length;
	}

}
