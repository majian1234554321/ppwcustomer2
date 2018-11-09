package com.yjhh.ppwcustomer.adapter;

import android.content.Context;
import android.view.View;

import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.bean.PhoneBean;

import java.util.List;

/**
 * SortAdapter
 * Created by D on 2017/6/7.
 */
public class SortAdapter extends CommonAdapter<PhoneBean> {
    public SortAdapter(Context context, List<PhoneBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(int position, CommonHolder holder, PhoneBean item) {
        if (item.isLetter) {
            holder.setViewVisibility(R.id.llyt_sort, View.VISIBLE);
            holder.setText(R.id.tv_letter, item.letter);
        } else {
            holder.setViewVisibility(R.id.llyt_sort, View.GONE);
        }
        holder.setText(R.id.tv_content, item.content);
    }
}
