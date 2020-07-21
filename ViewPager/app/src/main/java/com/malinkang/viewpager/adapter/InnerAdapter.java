package com.malinkang.viewpager.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.malinkang.viewpager.fragment.TestFragment;

import java.util.ArrayList;

/**
 * Created by malinkang on 14/11/2.
 */
public class InnerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Character> letters;

    public InnerAdapter(FragmentManager fm,ArrayList<Character> letters) {
        super(fm);
        this.letters=letters;
    }

    @Override
    public Fragment getItem(int i) {
        return TestFragment.newInstance(letters.get(i));
    }

    @Override
    public int getCount() {
        return letters.size();
    }


    @Override
    public int getItemPosition(Object object) {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
