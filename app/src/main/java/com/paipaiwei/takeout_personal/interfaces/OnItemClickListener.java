package com.paipaiwei.takeout_personal.interfaces;

import androidx.recyclerview.widget.RecyclerView;

public abstract class OnItemClickListener {
    abstract public void onItemClick(RecyclerView.ViewHolder vh, int position);
}
