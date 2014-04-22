package com.mamating.api;

import java.util.ArrayList;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mamating.bean.CountInfo;
import com.mamating.bean.Following;
import com.mamating.bean.FollowingInfo;
import com.mamating.constants.Constants;
import com.mamating.util.LogUtil;

/**
 * 好友相关Api
 * 
 * @author malinkang 2014年4月10日
 * 
 */
public class FriendShipApi extends BaseApi {

	public FriendShipApi(Context context) {

	}

	/**
	 * 获取某个用户关注人和粉丝的数量以及其它数量
	 * 
	 * @param uid
	 * @param
	 */
	public void getFollowCount(Integer uid) {
		RequestParams params = new RequestParams();
		params.put("Uid", uid);
		params.put("PHPSESSID", account.getSessionid());
		client.post(Constants.GET_FOLLOW_COUNT_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						if (content != null) {
							CountInfo countInfo = mGson.fromJson(content,
									CountInfo.class);
							countInfo.setCount_json(mGson.toJson(countInfo
									.getCount()));
							countInfo.save();
						}
					}

					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {

					}
				});
	}

	/**
	 * 获取关注人
	 * 
	 * @param uid
	 * @param lastId
	 */
	public void getFollowing(Integer uid, final String lastId) {
		RequestParams params = new RequestParams();
		// 传入Integer获取不到返回值
		params.put("Uid", uid + "");
		params.put("PHPSESSID", account.getSessionid());
		params.put("LastID", lastId);
		params.put("limit", "20");
		client.post(Constants.GET_FOLLOWING_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						if (content != null) {
							FollowingInfo followingInfo = mGson.fromJson(
									content, FollowingInfo.class);
							if (followingInfo != null) {
								ArrayList<Following> followings = followingInfo
										.getRespMsg();
								ActiveAndroid.beginTransaction();
								try {

									if (lastId == null) {
										// 清除数据库
										Following.delete(Following.class, 1);
									}
									for (Following following : followings) {
										following.setFollow_state_json(mGson
												.toJson(following
														.getFollow_state()));
										following.save();
									}
									ActiveAndroid.setTransactionSuccessful();
								} finally {
									ActiveAndroid.endTransaction();
								}
							}
						}
					}

					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {

					}
				});
	}

	/**
	 * 获取粉丝
	 * 
	 * @param uid
	 * @param lastId
	 */
	public void getFollower(String uid, String lastId) {
		RequestParams params = new RequestParams();
		params.put("Uid", uid);
		params.put("PHPSESSID", account.getSessionid());
		params.put("LastID", lastId);
		params.put("limit", "1");
		client.post(Constants.GET_FOLLOWER_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						LogUtil.e("获取粉丝列表返回：" + content);
					}

					@Override
					public void onFailure(Throwable error, String content) {
					}
				});
	}

	/**
	 * 获取互相关注列表
	 * 
	 * @param
	 */
	public void getFriend(String uid, String lastId) {
		RequestParams params = new RequestParams();
		params.put("Uid", uid);
		params.put("PHPSESSID", account.getSessionid());
		params.put("LastID", lastId);
		params.put("limit", "1");
		client.post(Constants.GET_FRIEDN_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						LogUtil.e("获取互相关注列表：" + content);
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
					}
				});
	}

	/**
	 * 获取推荐用户URL
	 * 
	 * @param gid
	 */
	public void getRecommand(String gid) {
		RequestParams params = new RequestParams();
		params.put("PHPSESSID", account.getSessionid());
		params.put("gid", gid);
		client.post(Constants.GET_RECOMMAND_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						LogUtil.e("获取推荐用户：" + content);
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
					}
				});
	}

	/**
	 * 添加或取消关注
	 * 
	 * @param
	 */
	public void addOrCancelFollow(String fid) {
		RequestParams params = new RequestParams();
		params.put("PHPSESSID", account.getSessionid());
		params.put("fid", fid);
		client.post(Constants.ADD_OR_CANCEL_FOLLOW_URL, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						LogUtil.e("添加或取消关注返回值：" + content);
						// 修改关注列表和粉丝列表数据库
						// 修改
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
					}
				});
	}

}
