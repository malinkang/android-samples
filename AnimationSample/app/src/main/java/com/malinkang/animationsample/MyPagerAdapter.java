package com.malinkang.animationsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ldzs_android_1 on 16/5/19.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private int[] resIds = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(resIds[position]);
    }

    @Override
    public int getCount() {
        return resIds.length;
    }
}
