package com.mamating.fragment;

import android.support.v4.app.Fragment;
import butterknife.ButterKnife;

import com.mamating.AppContext;

public class BaseFragment extends Fragment {
	protected AppContext application = AppContext.getInstance();

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
