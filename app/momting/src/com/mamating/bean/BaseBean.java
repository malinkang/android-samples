package com.mamating.bean;

import com.activeandroid.Model;
import com.google.gson.Gson;

public class BaseBean extends Model {
	protected static final Gson mGson = new Gson();
	protected Integer respCode;

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}
}
