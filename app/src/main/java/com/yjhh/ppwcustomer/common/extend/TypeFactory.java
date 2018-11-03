package com.yjhh.ppwcustomer.common.extend;

import android.view.View;
import com.yjhh.ppwcustomer.bean.Main1BannerBean;
import com.yjhh.ppwcustomer.bean.Main1ContentBean;
import com.yjhh.ppwcustomer.bean.Main1HeadBean;

import java.util.List;


public interface TypeFactory {
    //  定义所有的返回类型
    int type(Main1BannerBean bannerBean);

    int type(Main1ContentBean contentBean);

    BaseViewHolder createViewHolder(int type, View itemView);
}


