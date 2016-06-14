package com.malinkang.zhihudaily.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinkang.zhihudaily.R;
import com.malinkang.zhihudaily.StoryListView;
import com.malinkang.zhihudaily.internal.di.components.DaggerStoryComponent;
import com.malinkang.zhihudaily.internal.di.components.StoryComponent;
import com.malinkang.zhihudaily.model.StoryModel;
import com.malinkang.zhihudaily.presenter.StoryListPresenter;
import com.malinkang.zhihudaily.view.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements StoryListView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private MainAdapter adapter;

    private List<StoryModel> mStoryModelList;

    @Inject
    StoryListPresenter storyListPresenter;

    private Unbinder unbinder;

    private StoryComponent storyComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initializeInjector();
        setupRecyclerView();
        storyListPresenter.setView(this);
        storyListPresenter.initialize("20160614");
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStoryModelList = new ArrayList<>();
        adapter = new MainAdapter(this, mStoryModelList);
        mRecyclerView.setAdapter(adapter);

    }

    private void initializeInjector() {
        this.storyComponent = DaggerStoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        storyComponent.inject(this);
    }


    @Override
    public void renderStoryList(List<StoryModel> storyModelList) {
        mStoryModelList.clear();
        mStoryModelList.addAll(storyModelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewStory(StoryModel userModel) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
