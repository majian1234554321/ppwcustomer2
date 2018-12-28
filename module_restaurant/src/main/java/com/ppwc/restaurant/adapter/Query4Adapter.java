package com.ppwc.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ppwc.restaurant.R;
import com.ppwc.restaurant.bean.MeiShiHeadBean;
import com.ppwc.restaurant.bean.Query1Bean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Set;

public class Query4Adapter extends BaseAdapter {


    public Activity activity;
    public List<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX> list;
    private TextView tv;
    private ImageView iv;

    public Query4Adapter(Activity activity, List<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX> list) {
        this.activity = activity;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View view = View.inflate(activity, R.layout.foodlvadapter4, null);

        TextView tv_title = view.findViewById(R.id.tv_title);
        final TagFlowLayout tagFlowLayout = view.findViewById(R.id.TagFlowLayout);


        final Query4TagAdapter tagAdapter = new Query4TagAdapter(activity, list.get(position).nodes, tagFlowLayout);

        tagFlowLayout.setAdapter(tagAdapter);

        tv_title.setText(list.get(position).title);


        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position2, FlowLayout parent) {
                if (list.get(position).nodes.get(position2).check) {
                    list.get(position).nodes.get(position2).check = false;

                } else {
                    list.get(position).nodes.get(position2).check = true;
                }

                tagAdapter.notifyDataChanged();

                return true;
            }
        });


        return view;
    }


    public class Query4TagAdapter extends TagAdapter<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX.NodesBeanXX> {

        public Activity activity;
        public TagFlowLayout mFlowLayout;


        public List<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX.NodesBeanXX> data;

        public Query4TagAdapter(Activity activity, List<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX.NodesBeanXX> data, TagFlowLayout mFlowLayout) {
            super(data);
            this.data = data;
            this.activity = activity;
            this.mFlowLayout = mFlowLayout;
        }

        @Override
        public View getView(FlowLayout parent, int position, MeiShiHeadBean.QuerySearchBean.NodesBeanXXX.NodesBeanXX s) {

            TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.tag_tv_single,
                    mFlowLayout, false);
            tv.setText(s.title);

            if (s.check) {
                tv.setBackgroundResource(R.drawable.mr_check_bg);
            } else {
                tv.setBackgroundResource(R.drawable.mr_uncheck_bg);
            }

            return tv;
        }

        public void clear() {
            data.clear();
            notifyDataChanged();
        }

    }

}
