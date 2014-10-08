package com.flavienlaurent.vdh;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Flavien Laurent (flavienlaurent.com) on 23/08/13.
 */
public class DragLayout extends LinearLayout {

	private final static String TAG = DragLayout.class.getSimpleName();

	private final ViewDragHelper mDragHelper;

	private View mDragView;

	private boolean mDragEdge;
	private boolean mDragHorizontal;
	private boolean mDragCapture;
	private boolean mDragVertical;

	public DragLayout(Context context) {
		this(context, null);
	}

	public DragLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
	}

	@Override
	protected void onFinishInflate() {
		mDragView = findViewById(R.id.drag);
	}

	public void setDragHorizontal(boolean dragHorizontal) {
		mDragHorizontal = dragHorizontal;
	}

	public void setDragVertical(boolean dragVertical) {
		mDragVertical = dragVertical;
	}

	public void setDragEdge(boolean dragEdge) {
		mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
		mDragEdge = dragEdge;
	}

	public void setDragCapture(boolean dragCapture) {
		mDragCapture = dragCapture;
	}

	private class DragHelperCallback extends ViewDragHelper.Callback {

		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			if (mDragCapture) {
				return child == mDragView;
			}
			return true;
		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			invalidate();
		}

		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			super.onViewCaptured(capturedChild, activePointerId);
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			super.onViewReleased(releasedChild, xvel, yvel);
		}

		@Override
		public void onEdgeTouched(int edgeFlags, int pointerId) {
			super.onEdgeTouched(edgeFlags, pointerId);
		}

		@Override
		public void onEdgeDragStarted(int edgeFlags, int pointerId) {
			if (mDragEdge) {
				mDragHelper.captureChildView(mDragView, pointerId);
			}
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			if (mDragVertical) {

				final int topBound = getPaddingTop();
				final int bottomBound = getHeight() - mDragView.getHeight();

				final int newTop = Math.min(Math.max(top, topBound),
						bottomBound);
				Log.d(TAG, "top=" + top + "topBound=" + topBound
						+ "bottomBound=" + bottomBound + "newTop=" + newTop
						+ "dy=" + dy);
				return newTop;
			}
			return super.clampViewPositionVertical(child, top, dy);
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			if (mDragHorizontal || mDragCapture || mDragEdge) {

				final int leftBound = getPaddingLeft();
				final int rightBound = getWidth() - child.getWidth();
				// 内层控制左边界 外层控制右边界
				final int newLeft = Math.min(Math.max(left, leftBound),
						rightBound);
				Log.d(TAG, "left=" + left + "leftBound=" + leftBound
						+ "rightBound=" + rightBound + "newLeft=" + newLeft
						+ "dx=" + dx);
				return newLeft;
			}
			return super.clampViewPositionHorizontal(child, left, dx);
		}

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = MotionEventCompat.getActionMasked(ev);
		if (action == MotionEvent.ACTION_CANCEL
				|| action == MotionEvent.ACTION_UP) {
			mDragHelper.cancel();
			return false;
		}
		return mDragHelper.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		mDragHelper.processTouchEvent(ev);
		return true;
	}

}
