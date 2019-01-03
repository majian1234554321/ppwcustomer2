package com.ppwc.restaurant.mrbean;

import java.util.List;

public class RecommendProductBean {


    /**
     * items : [{"describe":"来咯哦咯哦哦哦大啊撸了几个呼啦粗发back 涂抹哦雷娇旅途雷娇啦啦啊不了雷娇呢婆婆说张奇是一种一无所有哭破后悔自己仔仔细细积极","id":1189,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"ifZan":false,"itemId":1189,"logoUrl":"http://192.168.2.200/asset/file/upload/20181228/3e3a06f7c0e3d6d6.png","name":"抱","orderBy":0,"price":5575255.5,"saleStatus":0,"saleStatusText":"上架","shopId":1009,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","zan":0,"zanText":"0推荐"}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 1
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
         * describe : 来咯哦咯哦哦哦大啊撸了几个呼啦粗发back 涂抹哦雷娇旅途雷娇啦啦啊不了雷娇呢婆婆说张奇是一种一无所有哭破后悔自己仔仔细细积极
         * id : 1189
         * ifBuy : false
         * ifCollect : false
         * ifMerRec : false
         * ifPlatRec : false
         * ifRead : false
         * ifZan : false
         * itemId : 1189
         * logoUrl : http://192.168.2.200/asset/file/upload/20181228/3e3a06f7c0e3d6d6.png
         * name : 抱
         * orderBy : 0
         * price : 5575255.5
         * saleStatus : 0
         * saleStatusText : 上架
         * shopId : 1009
         * shopName : 美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方
         * zan : 0
         * zanText : 0推荐
         */

        public String describe;
        public int id;
        public boolean ifBuy;
        public boolean ifCollect;
        public boolean ifMerRec;
        public boolean ifPlatRec;
        public boolean ifRead;
        public boolean ifZan;
        public String itemId;
        public String logoUrl;
        public String name;
        public int orderBy;
        public String price;
        public int saleStatus;
        public String saleStatusText;
        public int shopId;
        public String shopName;
        public int zan;
        public String zanText;
    }
}
