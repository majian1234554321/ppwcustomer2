package com.yjhh.ppwcustomer.adapter;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.bean.MyBuyCardInfoBean;

import java.util.List;

public class MyVpAdater extends PagerAdapter {
    private List<MyBuyCardInfoBean> list;
    private Context context;
    private View view;
    private TextView tv_no, tv_endTime;
    private ImageView iv_image;

    public MyVpAdater(Context context, List<MyBuyCardInfoBean> list) {
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

        view = View.inflate(context, R.layout.myvpadater, null);
        tv_no = view.findViewById(R.id.tv_No);

        iv_image = view.findViewById(R.id.iv_image);
        tv_endTime = view.findViewById(R.id.tv_endTime);
        tv_endTime.setText(list.get(position).expiredTime);


        Glide.with(context).load(list.get(position).imageUrl).into(iv_image);
        tv_no.setText(list.get(position).cardNo);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
