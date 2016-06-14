package com.malinkang.zhihudaily.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.malinkang.zhihudaily.R;
import com.malinkang.zhihudaily.domain.Story;
import com.malinkang.zhihudaily.model.StoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ldzs_android_1 on 16/6/3.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<StoryModel> mStoryList;

    private Context mContext;

    public MainAdapter(Context context, List<StoryModel> storyList) {
        mContext = context;
        mStoryList = storyList;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        StoryModel story = mStoryList.get(position);
        Glide.with(mContext).load(story.getImages().get(0)).into(holder.mImageView);
        holder.mTitleTextView.setText(story.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView mImageView;
        @BindView(R.id.tv_title)
        TextView mTitleTextView;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
