package com.paipaiwei.personal.bean;

import java.util.List;

public class NearByDataBean {

    /**
     * items : [{"coupons":[{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}],"distance":"0m","grade":10,"ifBuy":true,"ifNews":false,"ifRec":true,"labels":["武昌区"," 西餐牛排"],"name":"测试占位1"},{"coupons":[],"distance":"63m","grade":9,"ifBuy":false,"ifNews":false,"ifRec":false,"labels":[],"name":"测试占位2"},{"coupons":[],"distance":"98m","grade":8,"ifBuy":false,"ifNews":false,"ifRec":true,"labels":[],"name":"测试占位3"},{"coupons":[],"distance":"69m","grade":7,"ifBuy":true,"ifNews":false,"ifRec":false,"labels":["武昌区"," 西餐牛排"],"name":"测试占位4"},{"coupons":[{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}],"distance":"120m","grade":6,"ifBuy":false,"ifNews":false,"ifRec":true,"labels":[],"name":"测试占位5"},{"coupons":[],"distance":"35m","grade":5,"ifBuy":false,"ifNews":false,"ifRec":false,"labels":[],"name":"测试占位6"},{"coupons":[],"distance":"594m","grade":4,"ifBuy":true,"ifNews":false,"ifRec":true,"labels":["武昌区"," 西餐牛排"],"name":"测试占位7"},{"coupons":[],"distance":"560m","grade":3,"ifBuy":false,"ifNews":false,"ifRec":false,"labels":[],"name":"测试占位8"},{"coupons":[{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}],"distance":"72m","grade":2,"ifBuy":false,"ifNews":false,"ifRec":true,"labels":[],"name":"测试占位9"},{"coupons":[],"distance":"864m","grade":1,"ifBuy":true,"ifNews":false,"ifRec":false,"labels":["武昌区"," 西餐牛排"],"name":"测试占位10"}]
     * recordCount : 0
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * coupons : [{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}]
         * distance : 0m
         * grade : 10
         * ifBuy : true
         * ifNews : false
         * ifRec : true
         * labels : ["武昌区"," 西餐牛排"]
         * name : 测试占位1
         */

        public boolean ifPai;
        public String distance;
        public int grade;
        public boolean ifBuy;
        public boolean ifNews;
        public boolean ifRec;
        public String name;
        public List<CouponsBean> coupons;
        public List<String> labels;
        public String perCapita;
        public String logoUrl;

        public static class CouponsBean {
            /**
             * dis : 900
             * full : 1000
             * title : 券名
             * type : 1
             * value : 900
             * valueText : 900
             */

            public int dis;
            public int full;
            public String title;
            public int type;
            public int value;
            public String valueText;
        }
    }
}
