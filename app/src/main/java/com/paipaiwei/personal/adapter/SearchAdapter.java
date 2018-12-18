package com.paipaiwei.personal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.paipaiwei.personal.R;
import com.paipaiwei.personal.db.entity.HistoricalModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class SearchAdapter extends TagAdapter<HistoricalModel> {

    public Activity activity;
    public TagFlowLayout mFlowLayout;


    public List<HistoricalModel> data;

    public SearchAdapter(Activity activity, List<HistoricalModel> data, TagFlowLayout mFlowLayout) {
        super(data);
        this.data = data;
        this.activity = activity;
        this.mFlowLayout = mFlowLayout;
    }

    @Override
    public View getView(FlowLayout parent, int position, HistoricalModel s) {

        TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.tv_single,
                mFlowLayout, false);
        tv.setText(s.keyword);
        return tv;
    }

    public void clear() {
        data.clear();
        notifyDataChanged();
    }

}
