package com.yjhh.ppwcustomer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.yjhh.ppwcustomer.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class SearchAdapter extends TagAdapter<String> {

    public Activity activity;
    public TagFlowLayout mFlowLayout;


    public List<String> data;

    public SearchAdapter(Activity activity, List<String> data, TagFlowLayout mFlowLayout ) {
        super(data);
        this.data = data;
        this.activity = activity;
        this.mFlowLayout = mFlowLayout;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {

        TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.tv_single,
                mFlowLayout, false);
        tv.setText(s);
        return tv;
    }

    public void clear(){
        data.clear();
        notifyDataChanged();
    }

}
