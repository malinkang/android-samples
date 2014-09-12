package retrofit.malinkang.com.retrofitsample;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.malinkang.com.retrofitsample.Constants;
import retrofit.malinkang.com.retrofitsample.model.AccessToken;
import retrofit.malinkang.com.retrofitsample.model.User;

/**
 * Created by malinkang on 2014/9/12.
 */
public class GithubService {

    private OAuth mOauth;

    private API mApi;

    public GithubService() {
        RestAdapter oauthAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_OAUTH_URL)
                .build();
        mOauth = oauthAdapter.create(OAuth.class);
        RestAdapter apiAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();
        mApi = oauthAdapter.create(API.class);

    }

    public AccessToken getAccessToken(String client_id, String client_secret, String code, String redirect_url) {
        return mOauth.getAccessToken(client_id, client_secret, code, redirect_url);
    }

    public User getOrgMembers(String org){
        return mApi.getOrgMembers(org);
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

    private interface API{
        @GET("/orgs/{org}/members")
        User getOrgMembers(@Path("org") String org);
    }
}
