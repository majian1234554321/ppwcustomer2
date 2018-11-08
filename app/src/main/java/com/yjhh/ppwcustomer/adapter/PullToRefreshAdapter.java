package com.yjhh.ppwcustomer.adapter;



import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yjhh.ppwcustomer.R;

import java.util.List;


public class PullToRefreshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super( R.layout.main2fragmentadapter,   null);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_storeName,item);
    }



}
