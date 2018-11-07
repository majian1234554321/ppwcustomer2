package com.yjhh.ppwcustomer.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.bean.CouponBean;

import java.util.List;

public class CouponFragmentAdapter extends BaseQuickAdapter<CouponBean.ItemsBean, CouponFragmentAdapter.ViewHolder> {
    public Context context;
    public List<CouponBean.ItemsBean> data;

    public CouponFragmentAdapter(@Nullable List<CouponBean.ItemsBean> data, Context context) {
        super(data);
        this.context = context;
        this.data = data;

    }


    public void onRefresh(List<CouponBean.ItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void onLoad(List<CouponBean.ItemsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.couponfragmentadapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_status.setText(data.get(position).getTitle());
    }

    @Override
    protected void convert(CouponFragmentAdapter.ViewHolder helper, CouponBean.ItemsBean item) {

    }

    public class ViewHolder extends BaseViewHolder {

        private final TextView tv_status;

        public ViewHolder(View view) {
            super(view);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }
}
