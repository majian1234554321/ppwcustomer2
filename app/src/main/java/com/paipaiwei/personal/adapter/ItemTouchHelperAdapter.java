package com.paipaiwei.personal.adapter;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);



    void onItemDismiss(int position);
}
