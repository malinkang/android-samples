package com.malinkang.recyclerview.customlayoutmanager.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by malk on 2017/2/10.
 *
 */

public class MyFlowLayoutManager extends RecyclerView.LayoutManager {

    public static final String TAG = MyFlowLayoutManager.class.getSimpleName();
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private int mTotalHeight;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        fill(recycler);
    }

    private void fill(RecyclerView.Recycler recycler) {
        int lineTotalWidth = getPaddingLeft();
        mTotalHeight = getPaddingTop();
        for (int i = 0; i < getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);
            measureChildWithMargins(child, 0, 0);
            int height = getDecoratedMeasuredHeight(child);
            int width = getDecoratedMeasuredWidth(child);
            Rect rect = new Rect();
            if (i == 0) {
                mTotalHeight += height;
            }
            if (lineTotalWidth + width > getWidth() - getPaddingRight()) {
                //换行
                lineTotalWidth = getPaddingLeft()+width;
                mTotalHeight += height;
            }else {
                lineTotalWidth += width;
            }
            rect.left = lineTotalWidth - width;
            rect.top = mTotalHeight - height;
            rect.right = lineTotalWidth;
            rect.bottom = mTotalHeight;
            addView(child);
            layoutDecoratedWithMargins(child, rect.left, rect.top-mVerticalOffset, rect.right, rect.bottom-mVerticalOffset);
        }
    }

    private int mVerticalOffset;

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

        //dy
        //控制滑动范围
        Log.d(TAG,"dy="+dy+"mVerticalOffset="+mVerticalOffset);
        if (mVerticalOffset + dy < 0) {
            //向上滚动的dy大于偏移量 dy 设置为负的偏移量
            dy = -mVerticalOffset;
        } else if (mVerticalOffset + dy > mTotalHeight - getVerticalSpace()) {
            dy = mTotalHeight - getVerticalSpace() - mVerticalOffset;
        }

        offsetChildrenVertical(-dy);
        fill(recycler);
        mVerticalOffset += dy;
        return dy;
    }
    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

}
