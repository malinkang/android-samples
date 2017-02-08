package com.malinkang.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter implements OnItemChangeListener {
    private List<T> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private boolean mDrag;
    public static final int TYPE_HEADER = 100;
    public static final int TYPE_FOOTER = 101;
    private List<View> mHeaders = new ArrayList<>();
    private List<View> mFooters = new ArrayList<>();

    private ItemTouchHelper mItemTouchHelper;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list, RecyclerView.LayoutManager manager) {
        this(context, list);
        //http://blog.sqisland.com/2014/12/recyclerview-grid-with-header.html
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isHeader(position) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * <p>
     * 一般步骤
     * 1.创建ItemTouchHelper.Callback和ItemTouchHelper 并将ItemTouchHelper.Callback传给ItemTouchHelper，然后调用
     * attachToRecyclerView与RecyclerView进行关联
     * 2.当按下item触发startDrag(viewHolder)触发拖拽
     * 3.当调用ItemTouchHelper 的onMove和onSwiped时候刷新adapter
     * 4.当调用ItemTouchHelper的onSelectedChanged和clearView方法来设置item view的背景来标示选中和未选中
     * </p>
     */

    public BaseRecyclerViewAdapter(Context context, List<T> list, RecyclerView.LayoutManager manager, RecyclerView recyclerView, boolean drag) {
        this(context, list, manager);
        this.mDrag = drag;
        if (drag) {
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(this);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != TYPE_HEADER && viewType != TYPE_FOOTER) {
            AdapterItem<T> item = createAdapterItem();
            View view = LayoutInflater.from(mContext).inflate(item.getLayoutResId(viewType), parent, false);
            return new ViewHolder(view, createAdapterItem());
        } else {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderFooterViewHolder(frameLayout);
        }
    }

    public abstract AdapterItem<T> createAdapterItem();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View view;
        switch (holder.getItemViewType()){
            case TYPE_HEADER:
                view= mHeaders.get(position);
                prepareHeaderFooter((HeaderFooterViewHolder) holder, view);
                break;
            case TYPE_FOOTER:
                view = mFooters.get(position - mList.size() - mHeaders.size());
                prepareHeaderFooter((HeaderFooterViewHolder) holder, view);
                break;
            default:
                T t = mList.get(position - mHeaders.size());
                ((ViewHolder) holder).item.bindData(mContext, position, t, getItemViewType(position));
                if (mOnItemClickListener != null) {
                    holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, position - mHeaders.size()));
                }
                holder.itemView.setOnTouchListener((v, event) -> {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        if (mDrag) {
                            mItemTouchHelper.startDrag(holder);
                            mItemTouchHelper.startSwipe(holder);
                        }
                    }
                    return false;
                });
                break;
        }
    }

    private void prepareHeaderFooter(HeaderFooterViewHolder vh, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        vh.base.removeAllViews();
        vh.base.addView(view);
    }

    @Override
    public int getItemCount() {
        return mHeaders.size() + mList.size() + mFooters.size();
    }

    public boolean isHeader(int position) {
        return (position < mHeaders.size());
    }

    public boolean isFooter(int position) {
        return (position >= mHeaders.size() + mList.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            T t = mList.get(position - mHeaders.size());
            return createViewType(position, t);
        }
    }

    public int createViewType(int position, T t) {
        return 0;
    }

    public boolean hasFooter() {
        return mFooters.size() > 0;
    }

    public void addHeader(View header) {
        if (!mHeaders.contains(header)) {
            mHeaders.add(header);
            notifyItemInserted(mHeaders.size() - 1);
        }
    }

    public void removeHeader(View header) {
        if (mHeaders.contains(header)) {
            notifyItemRemoved(mHeaders.indexOf(header));
            mHeaders.remove(header);
        }
    }

    public void addFooter(View footer) {
        if (!mFooters.contains(footer)) {
            mFooters.add(footer);
            notifyItemInserted(mHeaders.size() + mList.size() + mFooters.size() - 1);
        }
    }

    public void removeFooter(View footer) {
        if (mFooters.contains(footer)) {
            notifyItemRemoved(mHeaders.size() + mList.size() + mFooters.indexOf(footer));
            mFooters.remove(footer);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder implements OnItemSelectedListener {
        protected AdapterItem<T> item;

        protected ViewHolder(View itemView, AdapterItem<T> item) {
            super(itemView);
            this.item = item;
            this.item.bindViews(itemView);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);

        }
    }

    public static class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
        FrameLayout base;

        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
            base = (FrameLayout) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


}