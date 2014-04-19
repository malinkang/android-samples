package com.mamating.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.mamating.AppContext;

public class BaseActivity extends ActionBarActivity {
	protected ActionBar mActionBar;
	protected AppContext application = AppContext.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBar = getSupportActionBar();

	}
}
