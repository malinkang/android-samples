package com.malinkang.recyclerview.customlayoutmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.malinkang.recyclerview.R;
import com.malinkang.recyclerview.base.BaseActivity;
import com.malinkang.recyclerview.customlayoutmanager.layoutmanager.RhombusView;
import com.malinkang.recyclerview.customlayoutmanager.layoutmanager.RhombusLayoutManager;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by malk on 2017/2/7.
 */

public class RhombusLayoutManagerActivity extends BaseActivity {

    private static final int[] COLORS = {0xff00FFFF, 0xffDEB887, 0xff5F9EA0,
            0xff7FFF00, 0xff6495ED, 0xffDC143C,
            0xff008B8B, 0xff006400, 0xff2F4F4F,
            0xffFF69B4, 0xffFF00FF, 0xffCD5C5C,
            0xff90EE90, 0xff87CEFA, 0xff800000};

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;
    private Adapter mAdapter = new Adapter();

    private int mCount = 49;
    private int mGroupSize = 7;
    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setLayoutManager(new RhombusLayoutManager(mGroupSize, true));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setupActionBar() {
        setTitle("RhombusLayoutManager");
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Holder holder, final int position) {
//            holder.item.setText("" + position);
            holder.item.setCardColor(randomColor());
            holder.text.setText("菜单" + position);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(RhombusLayoutManagerActivity.this, holder.text.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCount;
        }

        private int randomColor() {
            return COLORS[new Random().nextInt(COLORS.length)];
        }

        class Holder extends RecyclerView.ViewHolder {
            RhombusView item;
            TextView text;
            public Holder(View itemView) {
                super(itemView);
                item = (RhombusView) itemView.findViewById(R.id.item);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }

}
