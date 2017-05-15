package com.malinkang.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.malinkang.infiniteloopviewpager.InfiniteLoopCirclePageIndicator;
import com.malinkang.infiniteloopviewpager.InfiniteLoopViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.malinkang.viewpager.R.id.pageIndicatorView;

/**
 * Created by malk on 2017/5/12.
 */

public class InfiniteLoopSampleActivity extends FragmentActivity {

    public static final String TAG = InfiniteLoopSampleActivity.class.getSimpleName();
    @BindView(R.id.viewPager) InfiniteLoopViewPager mViewPager;
    @BindView(pageIndicatorView) InfiniteLoopCirclePageIndicator mPageIndicator;
    private List<Integer> mImageResIds = Arrays.asList(R.drawable.a, R.drawable.b, R.drawable.c);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_loop);
        ButterKnife.bind(this);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        mViewPager.setAdapter(adapter);
        mPageIndicator.setViewPager(mViewPager);
    }


    public class ImagePagerAdapter extends PagerAdapter {

        @BindView(R.id.image) ImageView mImageView;


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view =  LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_image, null);
            ButterKnife.bind(this,view);
            mImageView.setImageResource(mImageResIds.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
