package com.yjhh.ppwcustomer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyVpAdater extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public MyVpAdater(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageResource(list.get(position));
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
