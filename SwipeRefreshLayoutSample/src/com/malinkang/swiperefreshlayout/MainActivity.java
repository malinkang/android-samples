package com.malinkang.swiperefreshlayout;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A place holder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private SwipeRefreshLayout layout;
		String[] values = new String[] { "Android", "iPhone", "Blackberry",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
		ArrayList<String> list = new ArrayList<String>();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			layout = (SwipeRefreshLayout) inflater.inflate(
					R.layout.fragment_main, container, false);
			final ListView listView = (ListView) layout
					.findViewById(R.id.listview);

			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1, list);
			listView.setAdapter(adapter);

			layout.setColorScheme(R.color.holo_green_dark,
					R.color.holo_orange_dark, R.color.holo_blue_dark,
					R.color.holo_red_dark);
			layout.setOnRefreshListener(new OnRefreshListener() {

				@Override
				public void onRefresh() {
					Log.e(getClass().getSimpleName(), "refresh");
					for (int i = 0; i < values.length; i++) {
						list.add(values[i]);
					}
					adapter.notifyDataSetInvalidated();
					new GetLinks().execute();
				}
			});
			// Look up the AdView as a resource and load a request.

			return layout;
		}

		public class GetLinks extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();

			}

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				layout.setRefreshing(false);
			}
		}

	}

}
