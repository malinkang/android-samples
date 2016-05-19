package com.malinkang.animationsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements TextAdapter.OnItemClickListener {

    public static final String[] items = {"Tween Animation", "Property Animation", "Custom TypeEvaluator"
            , "ViewPager Animation"};
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;
    private TextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        adapter = new TextAdapter(this, items);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, TweenAnimationActivity.class);
                break;
            case 1:
                intent.setClass(this, PropertyAnimationActivity.class);
                break;
            case 2:
                intent.setClass(this, CustomTypeEvaluatorActivity.class);
                break;
            case 3:
                intent.setClass(this, ViewPagerAnimationActivity.class);
                break;
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
