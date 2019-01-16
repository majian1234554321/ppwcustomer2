package com.yjhh.common.bean;

import java.util.List;

public class QueryResultBean {


    /**
     * items : [{"coupons":[{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}],"distance":"0m","grade":10,"ifBrand":false,"ifBuy":true,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":true,"labelA":[{"code":"buy","title":"买","total":0,"value":""}],"labelB":[{"code":"rec","title":"推荐","total":0,"value":""}],"labels":["武昌区"," 西餐牛排"],"name":"测试占位1"},{"coupons":[],"distance":"6m","grade":9,"ifBrand":false,"ifBuy":false,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":false,"labelA":[],"labelB":[],"labels":[],"name":"测试占位2"},{"coupons":[],"distance":"148m","grade":8,"ifBrand":false,"ifBuy":false,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":true,"labelA":[],"labelB":[{"code":"rec","title":"推荐","total":0,"value":""}],"labels":[],"name":"测试占位3"},{"coupons":[],"distance":"177m","grade":7,"ifBrand":false,"ifBuy":true,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":false,"labelA":[{"code":"buy","title":"买","total":0,"value":""}],"labelB":[],"labels":["武昌区"," 西餐牛排"],"name":"测试占位4"},{"coupons":[{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}],"distance":"32m","grade":6,"ifBrand":false,"ifBuy":false,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":true,"labelA":[],"labelB":[{"code":"rec","title":"推荐","total":0,"value":""}],"labels":[],"name":"测试占位5"}]
     * pageCount : 0
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 0
     */

    public int pageCount;
    public QueryModelBean queryModel;
    public int recordCount;
    public List<ItemsBean> items;

    public static class QueryModelBean {
        /**
         * pageIndex : 0
         * pageSize : 10
         */

        public int pageIndex;
        public int pageSize;
    }

    public static class ItemsBean {
        /**
         * coupons : [{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"},{"dis":900,"full":1000,"title":"券名","type":1,"value":900,"valueText":"900"}]
         * distance : 0m
         * grade : 10
         * ifBrand : false
         * ifBuy : true
         * ifNews : false
         * ifPai : false
         * ifPre : false
         * ifRec : true
         * labelA : [{"code":"buy","title":"买","total":0,"value":""}]
         * labelB : [{"code":"rec","title":"推荐","total":0,"value":""}]
         * labels : ["武昌区"," 西餐牛排"]
         * name : 测试占位1
         */

        public String id;
        public String perCapita;
        public String logoUrl;
        public String distance;
        public int grade;
        public boolean ifBrand;
        public boolean ifBuy;
        public boolean ifNews;
        public boolean ifPai;
        public boolean ifPre;
        public boolean ifRec;
        public String name;
        public List<CouponsBean> coupons;
        public List<LabelABean> labelA;
        public List<LabelBBean> labelB;
        public List<String> labels;

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

        public static class LabelABean {
            /**
             * code : buy
             * title : 买
             * total : 0
             * value :
             */

            public String code;
            public String title;
            public int total;
            public String value;
        }

        public static class LabelBBean {
            /**
             * code : rec
             * title : 推荐
             * total : 0
             * value :
             */

            public String code;
            public String title;
            public int total;
            public String value;
        }
    }
}
