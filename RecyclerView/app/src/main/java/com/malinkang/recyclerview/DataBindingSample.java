package com.malinkang.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinkang.recyclerview.adapter.UserAdapter;
import com.malinkang.recyclerview.base.BaseActivity;
import com.malinkang.recyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by malk on 16/11/14.
 */

public class DataBindingSample extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<User> users;
    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            users.add(new User(15+i+"","abc"+i));
        }
        UserAdapter adapter = new UserAdapter(this,users);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setupActionBar() {
        mActionBar.setTitle("DataBindingSample");
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }


}
