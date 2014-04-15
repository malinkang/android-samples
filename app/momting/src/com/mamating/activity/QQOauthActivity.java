package com.mamating.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
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
import com.mamating.util.CommonUtility;
import com.mamating.util.Constants;

public class QQOauthActivity extends ActionBarActivity {
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

			// if (url.startsWith(Constants.REDIRECT_URL)) {
			//
			// handleRedirectUrl(view, url);
			// view.stopLoading();
			// return;
			// }
			super.onPageStarted(view, url, favicon);

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

	// private void handleRedirectUrl(WebView view, String url) {
	// Bundle values = CommonUtility.parseUrl(url);
	// Intent intent = new Intent();
	// intent.putExtras(values);
	// String access_token = values.getString("access_token");
	// String expires_time = values.getString("expires_in");
	// new LoginApi(this).getUserUid(access_token, expires_time);
	//
	// }

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
		parameters.put("client_id", Constants.QQ_APP_ID);
		parameters.put("redirect_uri", Constants.QQ_REDIRECT_URI);
		parameters.put("display", "mobile");
		parameters.put("response_type", "token");
		return Constants.QQ_AUTHORIZE_URL
				+ "?"
				+ CommonUtility.encodeUrl(parameters)
				+ "&scope=add_share,add_t,get_info,get_user_info,get_simple_userinfo,get_idollist";
	}

}
