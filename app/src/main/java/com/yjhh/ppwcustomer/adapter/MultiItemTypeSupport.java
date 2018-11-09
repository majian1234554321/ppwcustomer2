package com.yjhh.ppwcustomer.adapter;

/**
 * MultiItemTypeSupport for RecyclerView
 * Created by D on 2017/4/25.
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);
}

