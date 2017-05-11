package com.malinkang.viewpager.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.malinkang.viewpager.Fragment.TestFragment;

/**
 * Created by malinkang on 14/11/2.
 */
public class InnerAdapter extends FragmentPagerAdapter{

    private String[] letters;

    public InnerAdapter(FragmentManager fm,String[] letters) {
        super(fm);
        this.letters=letters;
    }

    @Override
    public Fragment getItem(int i) {
        return TestFragment.newInstance(letters[i]);
    }

    @Override
    public int getCount() {
        return letters.length;
    }
}
