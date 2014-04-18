package com.mamating.api;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mamating.bean.Account;
import com.mamating.bean.Bind;
import com.mamating.bean.BindInfo;
import com.mamating.bean.UserInfo;
import com.mamating.constants.Constants;

/**
 * 
 * @author malinkang 2014年4月15日
 * 
 */
public class LoginApi extends BaseApi {

	public void getSinaAccessToken(String code) {
		RequestParams params = new RequestParams();
		params.put("client_id", Constants.SINA_APP_KEY);
		params.put("client_secret", Constants.SINA_APP_SECRET);
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		params.put("redirect_uri", Constants.SINA_REDIRECT_URL);
		client.post(Constants.GET_SINA_ACCESSTOKEN, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						try {
							JSONObject obj = new JSONObject(content);
							String access_token = obj.optString("access_token");
							String uid = obj.optString("uid");
							getSinaUserInfo(access_token, uid);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
					}
				});
	}

	/**
	 * http://open.weibo.com/wiki/2/users/show
	 * 
	 * @param sinaUser
	 * @param handler
	 */
	private void getSinaUserInfo(final String access_token, final String uid) {
		RequestParams params = new RequestParams();
		params.put("access_token", access_token);
		params.put("uid", uid);
		client.get(Constants.GET_SINA_USER_INFO, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						try {
							JSONObject obj = new JSONObject(content);
							String screen_name = obj.getString("screen_name");
							String avatar_large = obj.getString("avatar_large");
							Login("sina", screen_name, uid, avatar_large,
									access_token);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
					}
				});
	}

	/**
	 * 
	 * @param access_token
	 * @param handler
	 */
	public void getOpenId(final String access_token) {
		RequestParams params = new RequestParams();
		params.put("access_token", access_token);
		client.post(Constants.GET_QQ_OPENID, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						String openid = content.replace("client_id=", "")
								.split("&openid=")[1];
						getQQUserInfo(access_token, openid);

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {

					}
				});
	}

	public void getQQUserInfo(final String access_token, final String openId) {
		RequestParams params = new RequestParams();
		params.put("access_token", access_token);
		params.put("oauth_consumer_key", Constants.QQ_APP_ID);
		params.put("openid", openId);
		client.get(Constants.GET_QQ_USER_INFO, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						try {
							JSONObject obj = new JSONObject(content);
							String nickname = obj.getString("nickname");
							String figureurl_2 = obj.getString("figureurl_2");
							Login("qzone", nickname, openId, figureurl_2,
									access_token);

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {

					}
				});
	}

	private void Login(String type, String username, String uid, String avatar,
			String oauth_token) {
		RequestParams params = new RequestParams();
		params.put("UserType", type);
		params.put("UserName", username);
		params.put("Uid", uid);
		params.put("UserAvatar", avatar);
		params.put("Oauth_Token", oauth_token);
		client.post(Constants.LOGIN_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {

						UserInfo userInfo = mGson.fromJson(content,
								UserInfo.class);
						if (userInfo != null) {
							checkBindInfo(userInfo.getRespMsg());
						} else {
							//
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {

					}
				});
	}

	public void checkBindInfo(final Account account) {
		RequestParams params = new RequestParams();
		params.put("PHPSESSID", account.getSessionid());
		client.post(Constants.CHECKBIND_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {

						BindInfo bindInfo = mGson.fromJson(content,
								BindInfo.class);
						if (bindInfo != null) {
							ArrayList<Bind> respMsg = bindInfo.getRespMsg();
							for (Bind bind : respMsg) {
								if (bind.getType().equals(Bind.SINA)) {
									account.setSina_uid(bind.getType_uid());
									account.setSina_oauth_token(bind
											.getOauth_token());
								} else {
									account.setQq_uid(bind.getType_uid());
									account.setQq_oauth_token(bind
											.getOauth_token());
								}
							}
							// 删除
							Account.delete(Account.class, 1);
							account.save();
						}
					}

					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {

					}
				});
	}
}
