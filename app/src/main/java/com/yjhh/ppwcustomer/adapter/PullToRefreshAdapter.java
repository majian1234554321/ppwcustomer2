package com.yjhh.ppwcustomer.adapter;



import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yjhh.ppwcustomer.R;

import java.util.List;

/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super( R.layout.main2fragmentadapter,   null);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_storeName,item);
    }



}
