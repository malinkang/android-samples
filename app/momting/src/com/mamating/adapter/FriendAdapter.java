package com.mamating.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.api.FriendShipApi;
import com.mamating.bean.FollowState;
import com.mamating.bean.Friend;
import com.mamating.constants.FriendTable;
import com.mamating.ui.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FriendAdapter extends CursorAdapter {

	private ImageLoader mImageLoader;

	private LayoutInflater mInflater;

	private FriendShipApi friendShipApi;

	public FriendAdapter(Context context, Cursor c) {
		super(context, c);
		mImageLoader = ImageLoader.getInstance();
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		friendShipApi = new FriendShipApi(context);
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
	public void bindView(View view, Context context, final Cursor cursor) {
		ViewHolder holer = (ViewHolder) view.getTag();
		if (holer == null) {
			holer = new ViewHolder(view);
			view.setTag(holer);
		}
		// 设置头像
		mImageLoader.displayImage(cursor.getString(cursor
				.getColumnIndex(FriendTable.COLUMN_AVATAR)), holer.avatar);
		// 昵称
		holer.nickname.setText(cursor.getString(cursor
				.getColumnIndex(FriendTable.COLUMN_UNAME)));
		// 获取关注状态
		String json = cursor.getString(cursor
				.getColumnIndex(FriendTable.COLUMN_FOLLOW_STATE_JSON));
		FollowState followState = Friend.getFollowStateFromJson(json);
		if (followState.getFollower() == 1 && followState.getFollowing() == 1) {
			holer.follow.setImageResource(R.drawable.each_follow_btn);
		} else if (followState.getFollower() == 1
				&& followState.getFollowing() == 0) {
			holer.follow.setImageResource(R.drawable.follow_btn);
		} else if (followState.getFollowing() == 1
				&& followState.getFollower() == 0) {
			holer.follow.setImageResource(R.drawable.has_follow_btn);
		}
		final Integer uid = cursor.getInt(cursor
				.getColumnIndex(FriendTable.COLUMN_UID));
		holer.follow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				friendShipApi.addOrCancelFollow(uid);
			}
		});
	}

}
