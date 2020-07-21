package com.malinkang.viewpager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        String arch = System.getProperty("os.arch");
        Log.d("MainActivity", "arch = " + arch);
        Log.d("MainActivity", "arch1 = " + Build.CPU_ABI);
        Log.d("MainActivity", "arch2 = " + Build.CPU_ABI2);
    }

    @OnClick(R.id.btn_infinite_loop)
    public void infiniteLoop() {
        Intent intent = new Intent(this, InfiniteLoopSampleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_dynamic_page)
    public void dynamicPage() {
        Intent intent = new Intent(this, DynamicPageSampleActivity.class);
        startActivity(intent);
    }
}
