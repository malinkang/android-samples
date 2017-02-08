package com.malinkang.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malinkang.recyclerview.base.BaseActivity;

import java.text.CollationKey;
import java.text.Collator;

import butterknife.BindView;

/**
 * Created by malk on 16/11/9.
 */

public class SortedListActivity extends BaseActivity {

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;
    private SortedListAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] provices = getResources().getStringArray(R.array.provinces);
        mAdapter = new SortedListAdapter(this, provices);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sortlist;
    }

    @Override
    public void setupActionBar() {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("SortedList");
    }


    private static class SortedListAdapter extends RecyclerView.Adapter<ProvinceViewHolder> {

        private Context mContext;

        private SortedList<String> mDataList;

        public SortedListAdapter(Context context, String[] dataList) {
            this.mContext = context;
            mDataList = new SortedList<>(String.class, new SortedListAdapterCallback<String>(this) {
                @Override
                public int compare(String o1, String o2) {

                    Collator collator = Collator.getInstance();//点击查看中文api详解
                    CollationKey key1 = collator.getCollationKey(o1.toString());
                    CollationKey key2 = collator.getCollationKey(o2.toString());

                    return key1.compareTo(key2);
                }

                @Override
                public boolean areContentsTheSame(String oldItem, String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areItemsTheSame(String item1, String item2) {
                    return item1.equals(item2);
                }
            });
            mDataList.beginBatchedUpdates();
            mDataList.addAll(dataList);
            mDataList.endBatchedUpdates();
        }

        @Override
        public ProvinceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ProvinceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProvinceViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    private static class ProvinceViewHolder extends RecyclerView.ViewHolder {
        public ProvinceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
