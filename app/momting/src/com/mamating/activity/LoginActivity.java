package com.mamating.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.mamating.R;
import com.mamating.api.LoginApi;

public class LoginActivity extends Activity {
	private LoginApi api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		api = new LoginApi();
	}

	@OnClick(R.id.login_with_qq)
	public void loginWithQQ() {
		startActivityForResult(new Intent(this, QQOauthActivity.class), 0);
	}

	@OnClick(R.id.login_with_sina)
	public void loginWithSina() {
		startActivityForResult(new Intent(this, SinaOauthActivity.class), 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0:
				String qq_url = data.getStringExtra("qq_url");
				String qq_access_token = qq_url.substring(
						qq_url.indexOf("=") + 1, qq_url.indexOf("&"));
				api.getOpenId(qq_access_token);
				break;
			case 1:
				String sina_url = data.getStringExtra("sina_url");
				String code = sina_url.split("code=")[1];
				api.getSinaAccessToken(code);
				break;

			}
		}
	}
}
