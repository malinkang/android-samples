package retrofit.malinkang.com.retrofitsample;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by malinkang on 2014/9/12.
 */
public class OauthActivity extends FragmentActivity {

    private WebView mWebView;

    private MenuItem refreshItem;

    private final static String TAG=OauthActivity.class.getSimpleName();

    public static String encodeUrl(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = param.keySet();
        boolean first = true;
        for (String key : keys) {
            String value = param.get(key);
            if (!TextUtils.isEmpty(value) || key.equals("description")
                    || key.equals("url")) {
                if (first) {
                    first = false;
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(param.get(key));

            }

        }

        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_oauth);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("新浪微博登陆");
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WeiboWebViewClient());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

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
        parameters.put("client_id", Constants.CLIENT_ID);
        parameters.put("redirect_uri", Constants.CALLBACK_URL);
        return Constants.AUTHORIZE_URL
                + "?"
                + encodeUrl(parameters)
                + "&scope=user,user:email,user:follow,public_repo,repo,repo_deployment,repo:status,delete_repo,notifications,gist" +
                "read:repo_hook,write:repo_hook,admin:repo_hook,read:org,write:org,admin:org,read:public_key,write:public_key,admin:public_key";
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
                //http://malinkang.com/?code=d314b8a66619cd86a900
                Intent intent = getIntent();
                intent.putExtra("url", url);
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
}


