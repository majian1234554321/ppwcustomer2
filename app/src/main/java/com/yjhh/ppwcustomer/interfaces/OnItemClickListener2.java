package com.yjhh.ppwcustomer.interfaces;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class OnItemClickListener2<T> {
    abstract public void onItemClick(RecyclerView.ViewHolder vh, List<T> data, int position);
}
