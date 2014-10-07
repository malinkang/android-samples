package retrofit.malinkang.com.retrofitsample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit.malinkang.com.retrofitsample.model.AccessToken;


public class MyActivity extends Activity implements View.OnClickListener {

    private final static String TAG = MyActivity.class.getSimpleName();

    private GithubService mGithubService;

    private AccessTokenAsyncTask mAccessTokenAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button getRequestBtn = (Button) findViewById(R.id.getRequestBtn);
        getRequestBtn.setOnClickListener(this);
        Button formEncodedRequestBtn = (Button) findViewById(R.id.formEncodedRequestBtn);
        formEncodedRequestBtn.setOnClickListener(this);
        mGithubService = new GithubService();
        mAccessTokenAsyncTask = new AccessTokenAsyncTask();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    String sina_url = data.getStringExtra("url");
                    String code = sina_url.split("code=")[1];
                    mAccessTokenAsyncTask.execute(code);
                    break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getRequestBtn:
                mGithubService.getContributors("square","retrofit");
                break;
            case R.id.formEncodedRequestBtn:
                Intent intent = new Intent(this, OauthActivity.class);
                startActivityForResult(intent, 0);
                break;
        }

    }

    private class AccessTokenAsyncTask extends AsyncTask<String, Void, AccessToken> {

        @Override
        protected AccessToken doInBackground(String... params) {
            AccessToken accessToken = mGithubService.getAccessToken(Constants.CLIENT_ID, Constants.CLIENT_SECRET, params[0], Constants.CALLBACK_URL);
            return accessToken;
        }

        @Override
        protected void onPostExecute(AccessToken accessToken) {
            super.onPostExecute(accessToken);
            Log.e(TAG, "accessToken-->" + accessToken.getAccess_token());
        }
    }

}
