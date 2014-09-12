package retrofit.malinkang.com.retrofitsample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit.malinkang.com.retrofitsample.model.AccessToken;
import retrofit.malinkang.com.retrofitsample.model.User;


public class MyActivity extends Activity implements View.OnClickListener {

    private final static String TAG = MyActivity.class.getSimpleName();

    private GithubService mGithubService;

    private AccessTokenAsyncTask mAccessTokenAsyncTask;

    private OrgMemberAsyncTask mOrgMemberAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        mGithubService = new GithubService();
        mAccessTokenAsyncTask = new AccessTokenAsyncTask();
        mOrgMemberAsyncTask = new OrgMemberAsyncTask();
        mOrgMemberAsyncTask.execute("GitHub");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String sina_url = data.getStringExtra("url");
            String code = sina_url.split("code=")[1];
            mAccessTokenAsyncTask.execute(code);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, OauthActivity.class);
        startActivityForResult(intent, 0);
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

    private class OrgMemberAsyncTask extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... params) {
//            User user = mGithubService.getOrgMembers(params[0]);
//            return user;
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
//            Log.e(TAG, user.getLogin() + "\t" + user.getAvatar_url());
        }
    }
}
