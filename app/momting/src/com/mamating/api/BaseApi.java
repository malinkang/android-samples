package com.mamating.api;

import java.security.KeyStore;

import com.loopj.android.http.AsyncHttpClient;

public class BaseApi {
	protected AsyncHttpClient client;

	public BaseApi() {
		// TODO Auto-generated constructor stub
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
