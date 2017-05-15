package com.malinkang.infiniteloopviewpager;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * A PagerAdapter that wraps around another PagerAdapter to handle paging wrap-around.
 */
class InfiniteLoopPagerAdapter extends PagerAdapter {


    private static final int INFINITE_RATIO = 400;

    private PagerAdapter adapter;


    public InfiniteLoopPagerAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getCount() {
        if (getRealCount() == 0) {
            return 0;
        }
        return getRealCount() * INFINITE_RATIO;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position % adapter.getCount();
        return adapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realPosition = position % adapter.getCount();
        adapter.destroyItem(container, realPosition, object);
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return adapter.isViewFromObject(view, object);
    }


    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        adapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return adapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        adapter.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int virtualPosition = position % adapter.getCount();
        return adapter.getPageTitle(virtualPosition);
    }

    @Override
    public float getPageWidth(int position) {
        return adapter.getPageWidth(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        adapter.setPrimaryItem(container, position, object);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return adapter.getItemPosition(object);
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    public int getRealCount() {
        return adapter.getCount();
    }

}