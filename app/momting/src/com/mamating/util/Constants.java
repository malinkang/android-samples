package com.mamating.util;

public class Constants {
	// 新浪AppKey
	public final static String SINA_APP_KEY = "496476891";
	// 新浪AppSercet
	public final static String SINA_APP_SECRET = "2a7a7b28f4567cd48bc23809cf4baa27";
	//
	public final static String SINA_AUTHORIZE_URL = "https://open.weibo.cn/oauth2/authorize";
	//
	public final static String SINA_REDIRECT_URL = "http://momting.com/";

	public final static String QQ_APP_ID = "100428945";
	public final static String QQ_APP_SECRET = "e8a4cde40fe1ff2f13adc0ba2932f075";

	public static final String QQ_AUTHORIZE_URL = "https://openmobile.qq.com/oauth2.0/m_authorize";
	public static final String QQ_REDIRECT_URI = "http://open.z.qq.com/moc2/success.jsp";
	// =================================================================
	// URL
	// ================================================================
	public final static String GET_SINA_ACCESSTOKEN = "https://api.weibo.com/oauth2/access_token";
	//
	public final static String GET_SINA_USER_INFO = "https://api.weibo.com/2/users/show.json";

	public final static String GET_QQ_OPENID = "https://graph.z.qq.com/moc2/me";

	public final static String GET_QQ_USER_INFO = "https://openmobile.qq.com/user/get_simple_userinfo";

	public final static String BASE_URL = "http://t.momting.com/";

	public final static String LOGIN_URL = BASE_URL
			+ "index.php?app=api&mod=Oauth&act=login";
}
