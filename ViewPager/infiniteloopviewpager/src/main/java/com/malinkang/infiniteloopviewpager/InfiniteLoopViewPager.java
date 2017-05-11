package com.malinkang.infiniteloopviewpager;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * A {@link ViewPager} that allows pseudo-infinite paging with a wrap-around effect. Should be used with an {@link
 * InfiniteLoopPagerAdapter}.
 * <p>
 * https://github.com/antonyt/InfiniteViewPager
 */
public class InfiniteLoopViewPager extends ViewPager {


    private int mIntervalMillis = 0;
    private boolean mInfiniteLoopEnable = false;

    private boolean isStop = true;

    private class AutoScrollTask implements Runnable {

        @Override
        public void run() {
            scrollToNextPage();
            mHander.postDelayed(this, mIntervalMillis);
        }
    }

    private Handler mHander = new Handler();

    private AutoScrollTask mAutoScrollTask = new AutoScrollTask();


    public InfiniteLoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InfiniteLoopViewPager);
        setInterval(a.getInt(R.styleable.InfiniteLoopViewPager_interval, 0));
        setInfiniteLoopEnable(a.getBoolean(R.styleable.InfiniteLoopViewPager_inflateLoop, false));
        a.recycle();
    }


    public void setInterval(int intervalMillis) {
        if (intervalMillis > 0) {
            mIntervalMillis = intervalMillis;
            startAutoScroll();
        }
    }

    public void startAutoScroll() {
        if (isStop) {
            mHander.postDelayed(mAutoScrollTask, mIntervalMillis);
            isStop = false;
        }
    }

    public void stopAutoScroll() {
        if (!isStop) {
            mHander.removeCallbacks(mAutoScrollTask);
            isStop = true;
        }
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            startAutoScroll();
        } else {
            stopAutoScroll();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
            final int action = ev.getAction();
            if (action == MotionEvent.ACTION_DOWN)
                stopAutoScroll();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
                startAutoScroll();

        return super.dispatchTouchEvent(ev);
    }

    public void setInfiniteLoopEnable(boolean infiniteLoopEnable) {
        mInfiniteLoopEnable = infiniteLoopEnable;
    }

    //滚动到下一页
    private void scrollToNextPage() {
        if (getAdapter() != null && getAdapter().getCount() > 0) {
            final int curr = getCurrentItem();
            int nextPage = 0;
            if (curr < getAdapter().getCount() - 1) {
                nextPage = curr + 1;
            }
            setCurrentItem(nextPage, true);
        }

    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (mInfiniteLoopEnable) {
            super.setAdapter(new InfiniteLoopPagerAdapter(adapter));
        } else {
            super.setAdapter(adapter);
        }
        // offset first element so that we can scroll to the left
        setCurrentItem(0);


    }


    @Override
    public void setCurrentItem(int item) {
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (getAdapter().getCount() == 0||!mInfiniteLoopEnable) {
            super.setCurrentItem(item, smoothScroll);
            return;
        }
        item = item % getAdapter().getCount();
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        if (getAdapter().getCount() == 0||!mInfiniteLoopEnable) {
            return super.getCurrentItem();
        }
        int position = super.getCurrentItem();
        if (getAdapter() instanceof InfiniteLoopPagerAdapter) {
            InfiniteLoopPagerAdapter infAdapter = (InfiniteLoopPagerAdapter) getAdapter();
            // Return the actual item position in the data backing InfiniteLoopPagerAdapter
            return (position % infAdapter.getRealCount());
        } else {
            return super.getCurrentItem();
        }
    }

    private int getOffsetAmount() {
        if (getAdapter().getCount() == 0) {
            return 0;
        }
        if (getAdapter() instanceof InfiniteLoopPagerAdapter) {
            InfiniteLoopPagerAdapter infAdapter = (InfiniteLoopPagerAdapter) getAdapter();
            // allow for 100 back cycles from the beginning
            // should be enough to create an illusion of infinity
            // warning: scrolling to very high values (1,000,000+) results in
            // strange drawing behaviour
            return infAdapter.getRealCount() * 100;
        } else {
            return 0;
        }
    }
}
