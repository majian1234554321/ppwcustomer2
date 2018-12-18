package com.paipaiwei.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paipaiwei.personal.R;
import com.paipaiwei.personal.bean.Main1FootBean;

public class Main2FragmentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Main2FragmentAdapter( ) {
        super(R.layout.main2fragmentadapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_storeName, "a");
//                .setText(R.id.tv_price,"b")
//                .setText(R.id.tv_distance,"c")
//                .setText(R.id.tv_info,"d");
    }


}
