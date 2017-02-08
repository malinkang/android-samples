package com.malinkang.recyclerview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malinkang.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by malk on 16/11/4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    @BindView(R.id.toolbar) Toolbar mToolBar;
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder=ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
        setupActionBar();
    }

    public abstract int getLayout();

    public abstract void setupActionBar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
