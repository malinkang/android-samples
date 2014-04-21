package com.mamating.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mamating.AppContext;
import com.mamating.R;
import com.mamating.adapter.CourseAdapter;
import com.mamating.bean.Course;
import com.mamating.util.ReadUtil;

public class AddCourseActivity extends BaseActivity {
	@InjectView(R.id.gridview)
	GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		ButterKnife.inject(this);
		boolean b = getIntent().getBooleanExtra("b", false);
		String json = ReadUtil.readFile(this, "course.json");
		Gson mGson = AppContext.getGson();
		ArrayList<Course> courses = mGson.fromJson(json,
				new TypeToken<List<Course>>() {
				}.getType());
		CourseAdapter adapter = new CourseAdapter(this, courses, b);
		mGridView.setAdapter(adapter);
	}
}
