package com.paipaiwei.personal.adapter;



import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paipaiwei.personal.R;


public class PullToRefreshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super( R.layout.main2fragmentadapter,   null);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_storeName,item);
    }



}
