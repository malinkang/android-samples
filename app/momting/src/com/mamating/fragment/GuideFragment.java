package com.mamating.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mamating.R;

/**
 * 
 * @author malinkang 2014-2-22 下午2:37:29
 * 
 */
public class GuideFragment extends Fragment {

	@InjectView(R.id.iv_guide)
	ImageView guideImageView;

	private final static String RES_ID = "res_id";

	private int resId;

	public static final GuideFragment newInstance(int resId) {
		GuideFragment fragment = new GuideFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(RES_ID, resId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resId = getArguments().getInt(RES_ID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guide, container, false);
		ButterKnife.inject(this, view);
		guideImageView.setImageResource(resId);
		return view;
	}
}
