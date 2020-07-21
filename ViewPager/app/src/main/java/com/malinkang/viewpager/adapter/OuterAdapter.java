package com.malinkang.viewpager.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.malinkang.viewpager.fragment.InnerViewPagerFragment;

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
