package com.mamating.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.activeandroid.content.ContentProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mamating.R;
import com.mamating.adapter.FriendAdapter;
import com.mamating.api.DataLoadFinishListener;
import com.mamating.api.FriendShipApi;
import com.mamating.bean.Following;
import com.mamating.constants.FriendTable;

/**
 * 关注的人的列表
 * 
 * @author malinkang 2014年4月21日
 * 
 */
public class FollowingListActivity extends BaseActivity implements
		DataLoadFinishListener, LoaderCallbacks<Cursor>,
		OnLastItemVisibleListener {
	@InjectView(R.id.list)
	PullToRefreshListView mPullToRefreshListView;
	private FriendAdapter adapter;
	private ListView mListView;
	private View mFooterView;
	private FriendShipApi friendShipApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.inject(this);
		adapter = new FriendAdapter(this, null);
		mListView = mPullToRefreshListView.getRefreshableView();
		mPullToRefreshListView.setAdapter(adapter);
		mPullToRefreshListView.setOnLastItemVisibleListener(this);
		mFooterView = View.inflate(this, R.layout.layout_listview_footer, null);
		mListView.addFooterView(mFooterView);
		friendShipApi = new FriendShipApi(this);
		friendShipApi.setmDataLoadFinishListener(this);
		friendShipApi.getFollowing(account.getUid(), null);
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(this, ContentProvider.createUri(
				Following.class, null), null,
				FriendTable.COLUMN_USER_ID + "=?",
				new String[] { account.getUid() + "" }, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		if (arg1 != null) {
			// 数据加载完毕
			hideListFooterView();
			adapter.swapCursor(arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);

	}

	@Override
	public void onLastItemVisible() {
		View view = mFooterView.findViewById(R.id.loading_progressbar);
		if (view.getVisibility() == View.VISIBLE) {
			return;
		}
		showListFooterView();
		Cursor cursor = adapter.getCursor();
		if (cursor.moveToLast()) {
			String lastId = cursor.getString(cursor
					.getColumnIndexOrThrow(FriendTable.COLUMN_FOLLOW_ID));
			friendShipApi.getFollowing(account.getUid(), lastId);
		}
	}

	private void showListFooterView() {
		View view = mFooterView.findViewById(R.id.loading_progressbar);
		view.setVisibility(View.VISIBLE);
	}

	private void hideListFooterView() {
		View view = mFooterView.findViewById(R.id.loading_progressbar);
		view.setVisibility(View.GONE);
	}

	@Override
	public void loadFinish() {
		hideListFooterView();

	}

}
