package com.mamating.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.mamating.AppContext;
import com.mamating.bean.Account;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseFragment extends Fragment {
	protected AppContext application = AppContext.getInstance();
	protected Gson mGson;
	protected ImageLoader mImageLoader;
	protected Account account;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mImageLoader = ImageLoader.getInstance();
		account = application.getAccount();
		mGson = AppContext.getGson();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
