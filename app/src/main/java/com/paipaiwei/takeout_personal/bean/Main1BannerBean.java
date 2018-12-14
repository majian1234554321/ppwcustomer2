package com.paipaiwei.takeout_personal.bean;

import com.paipaiwei.takeout_personal.common.extend.TypeFactory;
import com.paipaiwei.takeout_personal.common.extend.Visitable;

import java.util.List;

public class Main1BannerBean implements Visitable {
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

//    public Main1BannerBean(List<Main1HeadBean.BannersBean> list) {
//        this.list = list;
//    }
//
//    public  List<Main1HeadBean.BannersBean> list;
}
