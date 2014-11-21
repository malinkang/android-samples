package com.malinkang.viewpager.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.malinkang.viewpager.Fragment.TestFragment;

/**
 * Created by malinkang on 14/11/2.
 */
public class InnerAdapter extends FragmentPagerAdapter{

    private String letter;

    public InnerAdapter(FragmentManager fm,String letter) {
        super(fm);
        this.letter=letter;
    }

    @Override
    public Fragment getItem(int i) {
        return TestFragment.newInstance(letter);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
