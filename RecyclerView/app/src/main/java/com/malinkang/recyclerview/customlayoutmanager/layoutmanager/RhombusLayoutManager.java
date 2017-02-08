package com.malinkang.recyclerview.customlayoutmanager.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


public class RhombusLayoutManager extends RecyclerView.LayoutManager {

    public static final String TAG = RhombusLayoutManager.class.getSimpleName();
    public static final int DEFAULT_GROUP_SIZE = 5;

    private int mGroupSize;
    private int mHorizontalOffset;
    private int mVerticalOffset;
    private int mTotalWidth;
    private int mTotalHeight;
    private int mGravityOffset;
    private boolean isGravityCenter;

    private SparseArray<Rect> mItemFrames;

    public RhombusLayoutManager(boolean center) {
        this(DEFAULT_GROUP_SIZE, center);
    }

    public RhombusLayoutManager(int groupSize, boolean center) {
        mGroupSize = groupSize;
        isGravityCenter = center;
        mItemFrames = new SparseArray<>();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        View first = recycler.getViewForPosition(0);
        measureChildWithMargins(first, 0, 0);
        int itemWidth = getDecoratedMeasuredWidth(first);
        int itemHeight = getDecoratedMeasuredHeight(first);

        int firstLineSize = mGroupSize / 2 + 1;
        int secondLineSize = firstLineSize + mGroupSize / 2;
        if (isGravityCenter && firstLineSize * itemWidth < getHorizontalSpace()) {
            mGravityOffset = (getHorizontalSpace() - firstLineSize * itemWidth) / 2;
        } else {
            mGravityOffset = 0;
        }

        for (int i = 0; i < getItemCount(); i++) {
            Rect item = mItemFrames.get(i);
            if(item==null){
                item = new Rect();
                mItemFrames.put(i,item);
            }
            float coefficient = isFirstGroup(i) ? 1.5f : 1.f;
            //高度
            int offsetHeight = (int) ((i / mGroupSize) * itemHeight * coefficient);
            // 每一组的第一行
            if (isItemInFirstLine(i)) {
                // mGroupSize = 3  firstLineSize = 2
                // 0,1,0,1...
                int offsetInLine = i % mGroupSize;
                Log.d(TAG, "i = " + i + " offsetInLine = " + offsetInLine);
                item.set(mGravityOffset + offsetInLine * itemWidth, offsetHeight, mGravityOffset + offsetInLine * itemWidth + itemWidth, itemHeight + offsetHeight);
            } else {
                //每组的第二行
                int lineOffset = itemHeight / 2;
                int offsetInLine = (i < secondLineSize ? i : i % mGroupSize) - firstLineSize;
                //第二行 距离左边的距离比第一行距离左边的距离多半个
                item.set(mGravityOffset + offsetInLine * itemWidth + itemWidth / 2,
                        offsetHeight + lineOffset, mGravityOffset + offsetInLine * itemWidth + itemWidth + itemWidth / 2,
                        itemHeight + offsetHeight + lineOffset);
            }
        }

        mTotalWidth = Math.max(firstLineSize * itemWidth, getHorizontalSpace());
        int totalHeight = getGroupSize() * itemHeight;
        if (!isItemInFirstLine(getItemCount() - 1)) {
            totalHeight += itemHeight / 2;
        }
        mTotalHeight = Math.max(totalHeight, getVerticalSpace());
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        for (int i = 0; i < getItemCount(); i++) {
            Rect frame = mItemFrames.get(i);
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);
            layoutDecorated(scrap, frame.left - mHorizontalOffset, frame.top - mVerticalOffset, frame.right - mHorizontalOffset, frame.bottom - mVerticalOffset);
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        //dy
        Log.d(TAG, "dy = " + dy + " mVerticalOffset = " + mVerticalOffset + "mTotalHeight = " + mTotalHeight + "getVerticalSpace() = " + getVerticalSpace());
        //控制滑动范围
        if (mVerticalOffset + dy < 0) {
            dy = -mVerticalOffset;
        } else if (mVerticalOffset + dy > mTotalHeight - getVerticalSpace()) {
            dy = mTotalHeight - getVerticalSpace() - mVerticalOffset;
        }

        offsetChildrenVertical(-dy);
        fill(recycler, state);
        mVerticalOffset += dy;
        return dy;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        if (mHorizontalOffset + dx < 0) {
            dx = -mHorizontalOffset;
        } else if (mHorizontalOffset + dx > mTotalWidth - getHorizontalSpace()) {
            dx = mTotalWidth - getHorizontalSpace() - mHorizontalOffset;
        }

        offsetChildrenHorizontal(-dx);
        fill(recycler, state);
        mHorizontalOffset += dx;
        return dx;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    private boolean isItemInFirstLine(int index) {
        int firstLineSize = mGroupSize / 2 + 1;
        return index < firstLineSize || (index >= mGroupSize && index % mGroupSize < firstLineSize);
    }

    private int getGroupSize() {
        return (int) Math.ceil(getItemCount() / (float) mGroupSize);
    }

    private boolean isFirstGroup(int index) {
        return index < mGroupSize;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}

