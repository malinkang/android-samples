package com.mamating.activity;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.adapter.FollowingAdapter;

/**
 * 关注的人的列表
 * 
 * @author malinkang 2014年4月21日
 * 
 */
public class FollowingListActivity extends BaseActivity {
	@InjectView(R.id.list)
	ListView mListView;
	private FollowingAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.inject(this);
		// adapter = new FollowingAdapter(this);
		// mListView.setAdapter(adapter);

	}
}
