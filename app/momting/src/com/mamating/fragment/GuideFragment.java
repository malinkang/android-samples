package com.mamating.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.mamating.R;
import com.mamating.activity.LoginActivity;

/**
 * 
 * @author malinkang 2014-2-22 下午2:37:29
 * 
 */
public class GuideFragment extends Fragment {

	@InjectView(R.id.iv_guide)
	ImageView guideImageView;
	@InjectView(R.id.btn_enter)
	Button enterBtn;

	private final static String RES_ID = "res_id";

	private final static String IS_SHOW = "is_show";

	private int resId;

	private boolean isShow;

	public static final GuideFragment newInstance(int resId, boolean isShow) {
		GuideFragment fragment = new GuideFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(RES_ID, resId);
		bundle.putBoolean(IS_SHOW, isShow);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resId = getArguments().getInt(RES_ID);
		isShow = getArguments().getBoolean(IS_SHOW);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guide, container, false);
		ButterKnife.inject(this, view);
		guideImageView.setImageResource(resId);
		if (isShow) {
			enterBtn.setVisibility(View.VISIBLE);
		}
		return view;
	}

	@OnClick(R.id.btn_enter)
	public void enterLogin() {
		startActivity(new Intent(getActivity(), LoginActivity.class));
	}
}
