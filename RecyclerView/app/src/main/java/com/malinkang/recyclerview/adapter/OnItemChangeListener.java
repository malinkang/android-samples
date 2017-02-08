package com.malinkang.recyclerview.adapter;

/**
 * @author malinkang
 */
public interface OnItemChangeListener {
    //当item移动时调用
    boolean onItemMove(int fromPosition, int toPosition);

    //当item dismiss调用
    void onItemDismiss(int position);
}
