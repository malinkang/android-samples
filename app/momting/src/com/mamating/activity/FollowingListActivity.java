package com.mamating.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.activeandroid.content.ContentProvider;
import com.mamating.R;
import com.mamating.adapter.FollowingAdapter;
import com.mamating.api.FriendShipApi;
import com.mamating.bean.Following;

/**
 * 关注的人的列表
 * 
 * @author malinkang 2014年4月21日
 * 
 */
public class FollowingListActivity extends BaseActivity implements
		LoaderCallbacks<Cursor> {
	@InjectView(R.id.list)
	ListView mListView;
	private FollowingAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.inject(this);
		adapter = new FollowingAdapter(this, null);
		mListView.setAdapter(adapter);
		FriendShipApi friendShipApi = new FriendShipApi(this);
		friendShipApi.getFollowing(account.getUid(), null);
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(this, ContentProvider.createUri(
				Following.class, null), null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		if (arg1 != null) {
			adapter.swapCursor(arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);

	}
}
