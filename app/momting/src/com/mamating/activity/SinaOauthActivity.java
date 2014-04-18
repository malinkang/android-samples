package com.mamating.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import butterknife.InjectView;

import com.mamating.R;
import com.mamating.constants.Constants;
import com.mamating.util.CommonUtility;

public class SinaOauthActivity extends ActionBarActivity {
	private MenuItem refreshItem;
	@InjectView(R.id.webview)
	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("登陆");
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setWebViewClient(new WeiboWebViewClient());
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.oauth_actionbar, menu);
		refreshItem = menu.findItem(R.id.action_refresh);
		refresh();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			refresh();
			return true;
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// 刷新
	public void refresh() {
		mWebView.loadUrl("about:blank");
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageView iv = (ImageView) inflater.inflate(
				R.layout.refresh_action_view, null);
		Animation rotation = AnimationUtils.loadAnimation(this,
				R.anim.actionbar_item_refresh);
		iv.startAnimation(rotation);
		MenuItemCompat.setActionView(refreshItem, iv);
		mWebView.loadUrl(getOAuthUrl());
	}

	private class WeiboWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);

			if (url != null && url.contains("code")) {
				Intent intent = getIntent();
				intent.putExtra("sina_url", url);
				setResult(RESULT_OK, intent);
				finish();
			}

		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (!url.equals("about:blank")) {
				completeRefresh();
			}
		}
	}

	// 完成刷新
	private void completeRefresh() {
		if (MenuItemCompat.getActionView(refreshItem) != null) {
			MenuItemCompat.getActionView(refreshItem).clearAnimation();
			MenuItemCompat.setActionView(refreshItem, null);
		}
	}

	// 获取求取路径
	private String getOAuthUrl() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("client_id", Constants.SINA_APP_KEY);
		parameters.put("forcelogin", "true");
		parameters.put("redirect_uri", Constants.SINA_REDIRECT_URL);
		parameters.put("display", "mobile");
		return Constants.SINA_AUTHORIZE_URL
				+ "?"
				+ CommonUtility.encodeUrl(parameters)
				+ "&scope=email,direct_messages_write,direct_messages_read,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog";
	}

}
