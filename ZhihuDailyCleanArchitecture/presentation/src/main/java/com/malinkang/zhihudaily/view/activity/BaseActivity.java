package com.malinkang.zhihudaily.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.malinkang.zhihudaily.Application;
import com.malinkang.zhihudaily.internal.di.components.ApplicationComponent;
import com.malinkang.zhihudaily.internal.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }


  protected ApplicationComponent getApplicationComponent() {
    return ((Application)getApplication()).getApplicationComponent();
  }
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
