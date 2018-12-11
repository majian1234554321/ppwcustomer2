package com.yjhh.ppwcustomer.interfaces;

import androidx.recyclerview.widget.RecyclerView;

public abstract class OnItemClickListener {
    abstract public void onItemClick(RecyclerView.ViewHolder vh, int position);
}
