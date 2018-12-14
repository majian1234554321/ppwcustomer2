package com.paipaiwei.takeout_personal.common.extend;

import android.view.View;
import com.paipaiwei.takeout_personal.bean.Main1BannerBean;
import com.paipaiwei.takeout_personal.bean.Main1ContentBean;
import com.paipaiwei.takeout_personal.bean.Main1HeadBean;

import java.util.List;


public interface TypeFactory {
    //  定义所有的返回类型
    int type(Main1BannerBean bannerBean);

    int type(Main1ContentBean contentBean);

    BaseViewHolder createViewHolder(int type, View itemView);
}


