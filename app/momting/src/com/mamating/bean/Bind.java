package com.mamating.bean;

public class Bind {
	public final static String SINA = "sina";
	public final static String QQ = "qq";
	private Integer login_id;
	private Integer uid;
	private String type_uid;
	private String type;
	private String oauth_token;
	private String oauth_token_secret;
	private String is_sync;
	private String type_name;

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getType_uid() {
		return type_uid;
	}

	public void setType_uid(String type_uid) {
		this.type_uid = type_uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public String getOauth_token_secret() {
		return oauth_token_secret;
	}

	public void setOauth_token_secret(String oauth_token_secret) {
		this.oauth_token_secret = oauth_token_secret;
	}

	public String getIs_sync() {
		return is_sync;
	}

	public void setIs_sync(String is_sync) {
		this.is_sync = is_sync;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String toString() {
		return "Bind [login_id=" + login_id + ", uid=" + uid + ", type_uid="
				+ type_uid + ", type=" + type + ", oauth_token=" + oauth_token
				+ ", oauth_token_secret=" + oauth_token_secret + ", is_sync="
				+ is_sync + ", type_name=" + type_name + "]";
	}
}
