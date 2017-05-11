package com.malinkang.viewpager.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.malinkang.viewpager.Fragment.InnerViewPagerFragment;

/**
 * Created by malinkang on 14/11/2.
 */
public class OuterAdapter extends FragmentPagerAdapter {

    private String[] letters;

    public OuterAdapter(FragmentManager fm,String[] letters) {
        super(fm);
        this.letters=letters;
    }

    @Override
    public Fragment getItem(int i) {
        return InnerViewPagerFragment.newInstance(letters[i]);
    }

    @Override
    public int getCount() {
        return letters.length;
    }
}
