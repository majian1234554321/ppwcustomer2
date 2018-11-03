package com.yjhh.ppwcustomer.bean;

import com.yjhh.ppwcustomer.common.extend.TypeFactory;
import com.yjhh.ppwcustomer.common.extend.Visitable;

import java.util.List;

public class Main1ContentBean implements Visitable {
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    public Main1ContentBean(List<Main1HeadBean.ContentsBean> list) {
        this.list = list;
    }

    public  List<Main1HeadBean.ContentsBean> list;
}
