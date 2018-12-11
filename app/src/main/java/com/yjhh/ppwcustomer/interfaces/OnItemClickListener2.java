package com.yjhh.ppwcustomer.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class OnItemClickListener2<T> {
    abstract public void onItemClick(RecyclerView.ViewHolder vh, List<T> data, int position);
}
