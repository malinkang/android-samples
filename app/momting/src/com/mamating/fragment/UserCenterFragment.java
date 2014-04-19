package com.mamating.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mamating.R;

/**
 * 用户中心
 * 
 * @author malinkang 2014年4月10日
 * 
 */
public class UserCenterFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, null);
		ListView list = (ListView) view.findViewById(R.id.list);
		ArrayList<String> strs = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			strs.add("马林康");
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, strs);
		list.setAdapter(adapter);
		return view;
	}
}