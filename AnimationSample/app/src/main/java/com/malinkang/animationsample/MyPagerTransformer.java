package com.malinkang.animationsample;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ldzs_android_1 on 16/5/19.
 */
public class MyPagerTransformer implements ViewPager.PageTransformer {
    public static final String TAG = MyPagerTransformer.class.getSimpleName();

    /**
     * 当前View Position = 0 右侧View Position = 1
     * 从左向右滑动当前 Position递减 （0~-1） 右侧View Postiion递减（1,0)
     *
     *
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        Log.d(TAG, "position=" + position);
        int pageWidth = page.getWidth();
        ImageView imageView = (ImageView) page.findViewById(R.id.iv);


        if (position <= -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(1);

        } else if (position < 1) { // [-1,1]

            imageView.setTranslationX(-position * (pageWidth / 2)); //减慢ImageView移动速度

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(1);
        }
    }
}
