package com.paipaiwei.personal.bean;

import java.util.List;

public class InitBean {

    /**
     * advs : [{"imageUrl":"http://192.168.2.200/asset/file/upload/20181205/3fa7858c9c00a073.png","title":"1"},{"imageUrl":"http://192.168.2.200/asset/file/upload/20181205/3fa7858c9c00a073.png","title":"2"}]
     * applyShopUrl : http://192.168.2.200:8080/shop/apply
     * couponRuleUrl : http://192.168.2.200:8080/coupon/rule
     * helpIndexUrl : http://192.168.2.200:8080/help/index
     */

    public String applyShopUrl;
    public String couponRuleUrl;
    public String helpIndexUrl;
    public List<AdvsBean> advs;

    public static class AdvsBean {
        /**
         * imageUrl : http://192.168.2.200/asset/file/upload/20181205/3fa7858c9c00a073.png
         * title : 1
         */

        public String imageUrl;
        public String title;
    }
}
