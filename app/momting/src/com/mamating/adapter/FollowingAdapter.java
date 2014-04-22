package com.mamating.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.bean.FollowState;
import com.mamating.bean.Following;
import com.mamating.constants.FollowingTable;
import com.mamating.ui.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FollowingAdapter extends CursorAdapter {

	private ImageLoader mImageLoader;

	private LayoutInflater mInflater;

	public FollowingAdapter(Context context, Cursor c) {
		super(context, c);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	static class ViewHolder {
		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}

		@InjectView(R.id.iv_avatar)
		CircleImageView avatar;
		@InjectView(R.id.tv_nickname)
		TextView nickname;
		@InjectView(R.id.iv_follow)
		ImageView follow;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.layout_user_item, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holer = (ViewHolder) view.getTag();
		if (holer == null) {
			holer = new ViewHolder(view);
			view.setTag(holer);
		}
		// 设置头像
		mImageLoader.displayImage(cursor.getString(cursor
				.getColumnIndex(FollowingTable.COLUMN_AVATAR)), holer.avatar);
		// 获取关注状态
		FollowState followState = getFollowStateForPosition(cursor
				.getPosition());
		if (followState.getFollower() == 1 && followState.getFollowing() == 1) {
			holer.follow.setImageResource(R.drawable.each_follow_btn);
		} else if (followState.getFollower() == 1
				&& followState.getFollowing() == 0) {
			holer.follow.setImageResource(R.drawable.follow_btn);
		} else if (followState.getFollowing() == 1
				&& followState.getFollower() == 0) {
			holer.follow.setImageResource(R.drawable.has_follow_btn);
		}

	}

	private FollowState getFollowStateForPosition(int position) {
		FollowState followState = null;
		Cursor cursor = getCursor();
		if (cursor != null) {
			cursor.moveToPosition(position);
			String json = cursor.getString(cursor
					.getColumnIndex(FollowingTable.COLUMN_FOLLOW_STATE_JSON));
			followState = Following.getFollowStateFromJson(json);
		}
		return followState;
	}

}
