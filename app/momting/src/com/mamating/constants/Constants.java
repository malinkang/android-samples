package com.mamating.constants;

public class Constants {

	public final static int INIT_DATA = 0;

	public final static int LOAD_MORE = 1;
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

	public final static String CHECKBIND_URL = BASE_URL
			+ "index.php?app=api&mod=Oauth&act=checkToken";

	// 获取关注的人的列表URL
	public final static String GET_FOLLOWING_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=following";
	// 获取粉丝列表的URL
	public final static String GET_FOLLOWER_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=followers";
	// 获取关注人数量和粉丝数量
	public final static String GET_FOLLOW_COUNT_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=getFollowCount";
	// 获取互相关注的列表
	public final static String GET_FRIEDN_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=getFriends";

	// 推荐用户URL
	public final static String GET_RECOMMAND_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=userStar";
	// 添加关注或取消关注的URL
	public final static String ADD_OR_CANCEL_FOLLOW_URL = BASE_URL
			+ "index.php?app=api&mod=User&act=eachFollow";
}
