package com.malinkang.viewpager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by malk on 2017/5/10.
 */

public class MyTextView extends TextView {


    public static final String TAG = MyTextView.class.getSimpleName();
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize= MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG,"widthSize="+widthSize+",heightSize="+heightSize);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
