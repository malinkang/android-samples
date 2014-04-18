package com.mamating.api;

import java.security.KeyStore;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.mamating.AppContext;

public class BaseApi {
	protected AsyncHttpClient client;
	protected AppContext mApplication = AppContext.getInstance();
	protected Gson mGson = AppContext.getGson();

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
}
