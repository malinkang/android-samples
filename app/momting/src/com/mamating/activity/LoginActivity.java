package com.mamating.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.activeandroid.content.ContentProvider;
import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.api.LoginApi;
import com.mamating.bean.Account;

public class LoginActivity extends FragmentActivity implements
		LoaderCallbacks<Cursor> {
	private LoginApi api;
	@InjectView(R.id.oauth_login)
	LinearLayout oauthLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		api = new LoginApi();
		getSupportLoaderManager().initLoader(0, null, this);
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

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(this, ContentProvider.createUri(Account.class,
				null), null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		if (cursor != null && cursor.getCount() != 0) {
			Account account = Account.getAccountFromCursor(cursor);
			AppContext.getInstance().setAccount(account);
			startActivity(new Intent(this, HomeActivity.class));
			finish();
		} else {
			oauthLogin.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {

	}
}
