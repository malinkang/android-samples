package com.mamating.activity;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.content.ContentProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.adapter.TimelineListAdapter;
import com.mamating.bean.Timeline;
import com.mamating.util.ReadUtil;

public class TimelineActivity extends BaseActivity implements
		LoaderCallbacks<Cursor> {
	@InjectView(R.id.list)
	StickyListHeadersListView mListView;

	private TimelineListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		ButterKnife.inject(this);
		mAdapter = new TimelineListAdapter(this, null);
		mListView.setAdapter(mAdapter);
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new CursorLoader(this, ContentProvider.createUri(Timeline.class,
				null), null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		if (cursor != null && cursor.getCount() == 0) {
			String json = ReadUtil.readFile(this, "timeline.json");
			Gson mGson = AppContext.getGson();
			ArrayList<Timeline> timelines = mGson.fromJson(json,
					new TypeToken<ArrayList<Timeline>>() {
					}.getType());
			ActiveAndroid.beginTransaction();
			try {
				for (int i = 0; i < timelines.size(); i++) {
					timelines.get(i).setUser_json(
							mGson.toJson(timelines.get(i).getUser()));
					timelines.get(i).save();
				}
				ActiveAndroid.setTransactionSuccessful();
			} finally {
				ActiveAndroid.endTransaction();
			}
		} else {
			mAdapter.changeCursor(cursor);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		mAdapter.changeCursor(null);
	}
}
