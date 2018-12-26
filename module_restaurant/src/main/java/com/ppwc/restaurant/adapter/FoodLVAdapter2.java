package com.ppwc.restaurant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.ppwc.restaurant.R;


import java.util.List;

public class FoodLVAdapter2 extends BaseAdapter {

    public Context context;
    List<String> list;
    private TextView tv;
    private ImageView iv;
    public int choosePosition ;

    public FoodLVAdapter2(Context context, List<String> list , int  choosePosition) {
        this.context = context;
        this.list = list;
        this.choosePosition = choosePosition;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.foodlvadapter2, null);

        tv = view.findViewById(R.id.tv);

        iv = view.findViewById(R.id.iv);

        if (position == choosePosition) {
            tv.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            iv.setVisibility(View.VISIBLE);
        } else {
            tv.setTextColor(ContextCompat.getColor(context,R.color.all_9));
            iv.setVisibility(View.GONE);
        }

        return view;
    }



}
