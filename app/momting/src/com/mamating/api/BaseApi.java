package com.mamating.api;

import java.security.KeyStore;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.mamating.AppContext;
import com.mamating.bean.Account;

public class BaseApi {

	protected DataLoadFinishListener mDataLoadFinishListener;
	protected AsyncHttpClient client;
	protected AppContext mApplication = AppContext.getInstance();
	protected Gson mGson = AppContext.getGson();
	protected Account account = mApplication.getAccount();

	public BaseApi() {
		client = new AsyncHttpClient();
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			client.setSSLSocketFactory(new SSLSocketFactoryEx(trustStore));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setmDataLoadFinishListener(
			DataLoadFinishListener mDataLoadFinishListener) {
		this.mDataLoadFinishListener = mDataLoadFinishListener;
	}
}
