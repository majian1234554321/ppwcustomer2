package com.yjhh.ppwcustomer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.bean.Main1FootBean;

public class Main2FragmentAdapter extends BaseQuickAdapter<Main1FootBean.ItemsBean, BaseViewHolder> {
    public Main2FragmentAdapter() {
        super(R.layout.main2fragmentadapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, Main1FootBean.ItemsBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_price,item.getPrice()+"")
                .setText(R.id.tv_distance,item.getDistance())
                .setText(R.id.tv_info,item.getMemo());
    }


}
