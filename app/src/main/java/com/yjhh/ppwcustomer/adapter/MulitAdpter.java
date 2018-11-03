package com.yjhh.ppwcustomer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yjhh.ppwcustomer.common.extend.BaseViewHolder;
import com.yjhh.ppwcustomer.common.extend.ItemTypeFactory;
import com.yjhh.ppwcustomer.common.extend.Visitable;

import java.util.List;

public class MulitAdpter extends RecyclerView.Adapter<BaseViewHolder> {
    private ItemTypeFactory typeFactory;
    List<Visitable> mItems;
    /**
     * 构造函数
     */
    public MulitAdpter(List<Visitable> mData) {
        //  item工厂类 生产viewholder
        this.typeFactory = new ItemTypeFactory();
        mItems=mData;
    }

    @NonNull
    @Override public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent,false);
        return typeFactory.createViewHolder(viewType, view);
    }

    @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindViewData(mItems.get(position));
    }

    @Override public int getItemViewType(int position) {
        return mItems.get(position).type(typeFactory);
    }

    @Override public int getItemCount() {
        return (mItems != null ? mItems.size() : 0);
    }
}
