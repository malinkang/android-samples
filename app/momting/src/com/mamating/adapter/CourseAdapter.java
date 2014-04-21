package com.mamating.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mamating.R;
import com.mamating.bean.Course;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CourseAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Course> mCourses;
	private ImageLoader mImageLoader;
	private Boolean B;

	private class ViewHolder {
		ImageView cover;
		TextView name;
		TextView author;
		CheckBox select;
	}

	public CourseAdapter(Context context, ArrayList<Course> courses, Boolean b) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		B = b;
		mCourses = courses;
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return mCourses.size();
	}

	@Override
	public Object getItem(int position) {
		return mCourses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Course course = mCourses.get(position);
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.favorite_grid_item, null, false);
			holder = new ViewHolder();
			holder.cover = (ImageView) view.findViewById(R.id.cover);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.author = (TextView) view.findViewById(R.id.author);
			holder.select = (CheckBox) view.findViewById(R.id.select);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		mImageLoader.displayImage(course.getCover(), holder.cover);
		holder.name.setText(course.getName());
		holder.author.setText(course.getAuthor());
		if (B) {
			holder.select.setVisibility(View.VISIBLE);
		}
		return view;
	}

}
