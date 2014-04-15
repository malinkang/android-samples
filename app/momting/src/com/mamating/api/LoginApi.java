package com.mamating.api;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mamating.util.Constants;
import com.mamating.util.LogUtil;

/**
 * 
 * @author malinkang 2014年4月15日
 * 
 */
public class LoginApi {

	private AsyncHttpClient client;

	public LoginApi() {
		client = new AsyncHttpClient();
	}

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

	/**
	 * 
	 * @param qqUser
	 * @param handler
	 */
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

	/**
	 * QQ�û���½
	 * 
	 * @param qqUser
	 * @param handler
	 */
	public void Login(String type, String username, String uid, String avatar,
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
						LogUtil.e(content);

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {

					}
				});
	}

	// public void sinaLogin(final SinaUser sinaUser, final Handler handler) {
	// RequestParams params = new RequestParams();
	// params.put("UserType", "sina");
	// params.put("UserName", sinaUser.getUname());
	// params.put("Uid", sinaUser.getUid());
	// params.put("UserAvatar", sinaUser.getAvatar());
	// params.put("Oauth_Token", sinaUser.getAccess_token());
	// final HandlerManager manager = HandlerManager.getInstance(handler);
	// client.post(getURL(R.string.login), params,
	// new AsyncHttpResponseHandler() {
	// @Override
	// public void onSuccess(int arg0, String arg1) {
	// super.onSuccess(arg0, arg1);
	// LogUtil.i("�����û���½�ɹ�" + arg1);
	// try {
	// JSONObject obj = new JSONObject(arg1)
	// .getJSONObject("respMsg");
	// LoginUser user = new LoginUser();
	// user.setAvatar(obj.optString("avatar"));
	// user.setUid(obj.optString("uid"));
	// user.setUname(obj.optString("uname"));
	// user.setSessionid(obj.optString("sessionid"));
	// String isNew = obj.optString("is_new");
	// if (isNew.equals("0")) {
	// user.setNew(false);
	// } else {
	// user.setNew(true);
	// }
	// user.setSina_accesstoken(sinaUser.getAccess_token());
	// user.setSina_uid(sinaUser.getUid());
	// manager.sendDataParseSuccessMessage(user,
	// R.string.login);
	// } catch (Exception e) {
	// }
	// }
	//
	// @Override
	// public void onFailure(Throwable e, String arg1) {
	// super.onFailure(e, arg1);
	// }
	// });
	// }

	// /**
	// * ���ƽ̨����Ϣ
	// *
	// * @param user
	// * @param handler
	// */
	// public void checkBindInfo(final LoginUser user, final Handler handler) {
	// RequestParams params = new RequestParams();
	// params.put("PHPSESSID", user.getSessionid());
	// final HandlerManager manager = HandlerManager.getInstance(handler);
	// client.post(getURL(R.string.checkbind), params,
	// new AsyncHttpResponseHandler() {
	// @Override
	// public void onSuccess(int arg0, String arg1) {
	// // TODO Auto-generated method stub
	// super.onSuccess(arg0, arg1);
	// try {
	// JSONArray array = new JSONObject(arg1)
	// .getJSONArray("respMsg");
	// for (int i = 0; i < array.length(); i++) {
	// String type = array.getJSONObject(i).getString(
	// "type");
	// String oauth_token = array.getJSONObject(i)
	// .getString("oauth_token");
	// String type_uid = array.getJSONObject(i)
	// .getString("type_uid");
	// if (type.equals("sina")) {
	// user.setSina_accesstoken(oauth_token);
	// user.setSina_uid(type_uid);
	// } else {
	// user.setQq_accesstoken(oauth_token);
	// user.setQq_uid(type_uid);
	// }
	// }
	// manager.sendDataParseSuccessMessage(user,
	// R.string.checkbind);
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// @Override
	// public void onFailure(Throwable arg0, String arg1) {
	// super.onFailure(arg0, arg1);
	// }
	//
	// });
	// }

}
