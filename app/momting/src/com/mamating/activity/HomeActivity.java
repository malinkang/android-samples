package com.mamating.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost.OnTabChangeListener;

import com.mamating.R;
import com.mamating.fragment.FindFragment;
import com.mamating.fragment.HomeFragment;
import com.mamating.fragment.NotificationFragment;
import com.mamating.fragment.UserCenterFragment;

public class HomeActivity extends BaseActivity implements OnTabChangeListener,
		OnCheckedChangeListener {

	private FragmentTabHost mTabHost;
	// Tab选项卡的文字
	private String tabLabels[] = { "微信", "通信录", "发现", "我" };
	private Class<?> fragments[] = { HomeFragment.class, FindFragment.class,
			NotificationFragment.class, UserCenterFragment.class };

	private RadioButton main_tab_weixin;

	private RadioButton main_tab_address;

	private RadioButton main_tab_find_friend;

	private RadioButton main_tab_more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		main_tab_weixin = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_address = (RadioButton) findViewById(R.id.main_tab_find);
		main_tab_find_friend = (RadioButton) findViewById(R.id.main_tab_friend);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_setting);
		main_tab_weixin.setOnCheckedChangeListener(this);
		main_tab_address.setOnCheckedChangeListener(this);
		main_tab_find_friend.setOnCheckedChangeListener(this);
		main_tab_more.setOnCheckedChangeListener(this);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		for (int i = 0; i < 4; i++) {
			mTabHost.addTab(
					mTabHost.newTabSpec(tabLabels[i])
							.setIndicator(tabLabels[i]), fragments[i], null);
		}
		mTabHost.setOnTabChangedListener(this);

	}

	@Override
	public void onTabChanged(String tabId) {
		main_tab_weixin.setChecked(false);
		main_tab_address.setChecked(false);
		main_tab_find_friend.setChecked(false);
		main_tab_more.setChecked(false);
		if (tabId.equals("微信")) {
			main_tab_weixin.setChecked(true);
		} else if (tabId.equals("通信录")) {
			main_tab_address.setChecked(true);
		} else if (tabId.equals("发现")) {
			main_tab_find_friend.setChecked(true);
		} else if (tabId.equals("我")) {
			main_tab_more.setChecked(true);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.main_tab_home:
				mTabHost.setCurrentTab(0);
				break;
			case R.id.main_tab_find:
				mTabHost.setCurrentTab(1);
				break;
			case R.id.main_tab_friend:
				mTabHost.setCurrentTab(2);
				break;
			case R.id.main_tab_setting:
				mTabHost.setCurrentTab(3);
				break;
			}
		}
	}

}
