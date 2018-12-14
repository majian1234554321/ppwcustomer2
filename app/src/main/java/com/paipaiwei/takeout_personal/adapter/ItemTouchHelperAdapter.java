package com.paipaiwei.takeout_personal.adapter;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);



    void onItemDismiss(int position);
}
