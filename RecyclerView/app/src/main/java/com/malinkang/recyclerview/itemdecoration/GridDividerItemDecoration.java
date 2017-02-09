package com.malinkang.recyclerview.itemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by malk on 16/11/8.
 */

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {


    private final Rect mBounds = new Rect();

    private Drawable mDivider;


    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    int space;

    public GridDividerItemDecoration(Context context, int space) {
        this.space = space;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);
        int position = parent.getChildAdapterPosition(view);
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanSize = layoutParams.getSpanSize();//获取spanSize
        int spanIndex = layoutParams.getSpanIndex();
        int totalSpanSize = gridLayoutManager.getSpanCount();
        if (spanIndex == 0 && spanSize + spanIndex == totalSpanSize) {//只有一个
            outRect.left = space;
            outRect.right = space;
        } else if (spanIndex == 0) {//最左边
            outRect.left = space;
            outRect.right = space / 2;
        } else if (spanSize + spanIndex == totalSpanSize) {//最右边
            outRect.left = space / 2;
            outRect.right = space;
        } else if (spanIndex > 0 && spanSize + spanIndex < totalSpanSize) {
            outRect.left = space / 2;
            outRect.right = space / 2;
        }
        outRect.top = space;

    }
}
