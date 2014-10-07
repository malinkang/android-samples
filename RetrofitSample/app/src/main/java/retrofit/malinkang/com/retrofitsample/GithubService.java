package retrofit.malinkang.com.retrofitsample;

import android.os.Handler;
import android.util.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.malinkang.com.retrofitsample.model.AccessToken;
import retrofit.malinkang.com.retrofitsample.model.Contributor;
import retrofit.malinkang.com.retrofitsample.model.User;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by malinkang on 2014/9/12.
 */
public class GithubService {

    private final static String TAG=GithubService.class.getSimpleName();

    private OAuth mOauth;

    private Api mApi;

    public GithubService() {
        RestAdapter oauthAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_OAUTH_URL)
                .build();
        mOauth = oauthAdapter.create(OAuth.class);
        RestAdapter apiAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();
        mApi = apiAdapter.create(Api.class);

    }

    public AccessToken getAccessToken(String client_id, String client_secret, String code, String redirect_url) {
        return mOauth.getAccessToken(client_id, client_secret, code, redirect_url);
    }

    public void getContributors(String owner,String repo){
         mApi.getContributors(owner,repo,new Callback<List<Contributor>>(){


             @Override
             public void success(List<Contributor> contributors, Response response) {

                 for(Contributor contributor:contributors){
                     Log.d(TAG,"getContributors success--->"+contributor.getLogin()+"\t"+contributor.getContributions());

                 }
             }

             @Override
             public void failure(RetrofitError error) {
                 Log.d(TAG,"getContributors failure--->"+error.getMessage());
             }
         });
    }




    private interface OAuth {
        @Headers("Accept: application/json")
        @FormUrlEncoded
        @POST(Constants.ACCESS_TOKEN_URL)
        AccessToken getAccessToken(
                @Field("client_id") String client_id,
                @Field("client_secret") String client_secret,
                @Field("code") String code,
                @Field("redirect_uri") String redirect_uri
        );

    }

    private interface Api {
        //异步操作
        @GET("/repos/{owner}/{repo}/contributors")
       void getContributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callBack);

    }
}
