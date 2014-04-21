package com.mamating.adapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.bean.Timeline;
import com.mamating.bean.User2;
import com.mamating.constants.TimelineTable;
import com.mamating.media.PlayerEngineImpl;
import com.mamating.media.PlayerEngineListener;
import com.mamating.ui.CircleImageView;
import com.mamating.util.CommonUtility;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 动态信息列表适配器
 * 
 * @author baoyz
 * 
 *         2014-2-23 下午4:42:54
 * 
 */
public class TimelineListAdapter extends CursorAdapter implements
		StickyListHeadersAdapter {

	private final LayoutInflater mInflater;
	private final ImageLoader mImageLoader;
	private final PlayerEngineImpl mPlayerEngineImpl;

	public TimelineListAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		mImageLoader = ImageLoader.getInstance();
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPlayerEngineImpl = AppContext.getInstance().getmPlayerEngineImpl();

	}

	class ViewHolder {
		ImageView contentIv;
		ImageView playIv;
		ProgressBar loading;
		SeekBar seekBar;
		TextView currentDurationTv;
		TextView totalDurationTv;

	}

	class HeaderViewHolder {
		CircleImageView avatarIv;
		TextView nicknameTv;
		TextView createTimeTv;
	}

	private User2 getUserForPosition(int position) {
		User2 user = null;
		Cursor cursor = getCursor();

		if (cursor != null) {
			cursor.moveToPosition(position);
			String json = cursor.getString(cursor
					.getColumnIndex(TimelineTable.COLUMN_USER_JSON));
			user = Timeline.getUserFromJson(json);
		}

		return user;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder = null;
		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = mInflater.inflate(
					R.layout.layout_timeline_header_item, parent, false);
			holder.avatarIv = (CircleImageView) convertView
					.findViewById(R.id.iv_avatar);
			holder.nicknameTv = (TextView) convertView
					.findViewById(R.id.tv_nickname);
			holder.createTimeTv = (TextView) convertView
					.findViewById(R.id.tv_create_time);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}

		User2 user = getUserForPosition(position);
		if (user != null) {
			mImageLoader.displayImage(user.getAvatar(), holder.avatarIv);
			holder.nicknameTv.setText(user.getNickname());
		}
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		return position;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.layout_timeline_content_item, null);
	}

	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.contentIv = (ImageView) view.findViewById(R.id.iv_content);
			holder.playIv = (ImageView) view.findViewById(R.id.iv_play);
			holder.loading = (ProgressBar) view.findViewById(R.id.pb_loading);
			holder.seekBar = (SeekBar) view.findViewById(R.id.seekbar);
			holder.currentDurationTv = (TextView) view
					.findViewById(R.id.tv_play_duration);

			holder.totalDurationTv = (TextView) view
					.findViewById(R.id.tv_total_duration);
			view.setTag(holder);
		}
		// 加载图片
		holder.seekBar.setVisibility(View.GONE);
		holder.currentDurationTv.setVisibility(View.GONE);
		holder.totalDurationTv.setVisibility(View.GONE);
		mImageLoader.displayImage(cursor.getString(cursor
				.getColumnIndex(TimelineTable.COLUMN_PIC)), holder.contentIv);
		final MyOnSeekBarChangeListener myOnSeekBarChangeListener = new MyOnSeekBarChangeListener();
		holder.seekBar.setOnSeekBarChangeListener(myOnSeekBarChangeListener);
		final MyPlayerEngineListener myPlayerEngineListener = new MyPlayerEngineListener(
				holder);

		final String url = cursor.getString(cursor
				.getColumnIndex(TimelineTable.COLUMN_AUDIO));

		if (mPlayerEngineImpl.isPlaying()
				&& url.equals(mPlayerEngineImpl.getUrl())) {
			holder.playIv.setImageResource(R.drawable.player_pause);
			holder.seekBar.setVisibility(View.VISIBLE);
			holder.currentDurationTv.setVisibility(View.VISIBLE);
			holder.totalDurationTv.setVisibility(View.VISIBLE);
		}

		holder.playIv.setImageResource(R.drawable.player_play);

		holder.playIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mPlayerEngineImpl.setListener(myPlayerEngineListener);
				mPlayerEngineImpl.play(url);
			}
		});

	}

	private class MyOnSeekBarChangeListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			//
			mPlayerEngineImpl.seekTo(seekBar.getProgress());

		}

	}

	private class MyPlayerEngineListener implements PlayerEngineListener {
		private final ViewHolder mHolder;

		public MyPlayerEngineListener(ViewHolder holder) {
			mHolder = holder;
		}

		@Override
		public void onTrackStreamError() {

		}

		@Override
		public void onTrackStop() {
			mHolder.seekBar.setVisibility(View.GONE);
			mHolder.currentDurationTv.setVisibility(View.GONE);
			mHolder.totalDurationTv.setVisibility(View.GONE);
			mHolder.seekBar.setProgress(0);
			mHolder.playIv.setImageResource(R.drawable.player_play);

		}

		@Override
		public boolean onTrackStart() {
			mHolder.playIv.setImageResource(R.drawable.player_pause);
			return true;
		}

		@Override
		public void onTrackProgress(int seconds) {

			mHolder.seekBar.setProgress(seconds);
			mHolder.currentDurationTv.setText(CommonUtility
					.audioDurationFromat(seconds));
		}

		@Override
		public void onTrackPause() {
			mHolder.playIv.setImageResource(R.drawable.player_play);
		}

		@Override
		public void onTrackChanged(int totalSeconds) {
			mHolder.seekBar.setVisibility(View.VISIBLE);
			mHolder.currentDurationTv.setVisibility(View.VISIBLE);
			mHolder.totalDurationTv.setVisibility(View.VISIBLE);
			mHolder.seekBar.setMax(totalSeconds);
			mHolder.totalDurationTv.setText(CommonUtility
					.audioDurationFromat(totalSeconds));
		}

		@Override
		public void onTrackBuffering(int percent) {

		}
	};
}
