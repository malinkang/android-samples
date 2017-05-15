package com.malinkang.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_infinite_loop)
    public void infiniteLoop(){
        Intent intent = new Intent(this,InfiniteLoopSampleActivity.class);
        startActivity(intent);
    }
}
