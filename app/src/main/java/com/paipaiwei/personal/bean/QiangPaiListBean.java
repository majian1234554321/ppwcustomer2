package com.paipaiwei.personal.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.paipaiwei.personal.adapter.QiangPaiListAdapter;

import java.util.List;

public class QiangPaiListBean  {

    /**
     * items : [{"begin":1547794800,"end":1547816399,"items":[{"beginTime":1547794800,"costPrice":0.02,"count":0,"countText":"剩余 50份","describe":"阿萨德噶噶大概","endTime":1547816399,"id":1001,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","markPrice":99,"memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":1,"statusText":"立即抢拍","time":18531,"title":"抢拍3","total":50},{"beginTime":1547794800,"costPrice":0.02,"count":10,"countText":"剩余 30份","describe":"噶那啥咖妃","endTime":1547816399,"id":1000,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","markPrice":1231,"memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":1,"statusText":"立即抢拍","time":18531,"title":"抢拍8","total":40},{"beginTime":1547794800,"costPrice":0.02,"count":30,"countText":"剩余 0份","describe":"1未签二群无","endTime":1547816399,"id":1002,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":2,"statusText":"已拍完","time":0,"title":"抢拍4","total":30},{"beginTime":1547794800,"costPrice":0.02,"count":45,"countText":"剩余 0份","describe":"全发顺丰","endTime":1547816399,"id":1003,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":2,"statusText":"已拍完","time":0,"title":"抢拍5","total":45}],"name":"当前抢拍","status":1,"statusText":"进行中","testName":"2019-01-18 15:00:00-2019-01-18 20:59:59","time":0}]
     * pageCount : 0
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 0
     */

    public int pageCount;
    public QueryModelBean queryModel;
    public int recordCount;
    public List<ItemsBeanX> items;

    public static class QueryModelBean {
        /**
         * pageIndex : 0
         * pageSize : 10
         */

        public int pageIndex;
        public int pageSize;
    }

    public static class ItemsBeanX {
        /**
         * begin : 1547794800
         * end : 1547816399
         * items : [{"beginTime":1547794800,"costPrice":0.02,"count":0,"countText":"剩余 50份","describe":"阿萨德噶噶大概","endTime":1547816399,"id":1001,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","markPrice":99,"memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":1,"statusText":"立即抢拍","time":18531,"title":"抢拍3","total":50},{"beginTime":1547794800,"costPrice":0.02,"count":10,"countText":"剩余 30份","describe":"噶那啥咖妃","endTime":1547816399,"id":1000,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","markPrice":1231,"memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":1,"statusText":"立即抢拍","time":18531,"title":"抢拍8","total":40},{"beginTime":1547794800,"costPrice":0.02,"count":30,"countText":"剩余 0份","describe":"1未签二群无","endTime":1547816399,"id":1002,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":2,"statusText":"已拍完","time":0,"title":"抢拍4","total":30},{"beginTime":1547794800,"costPrice":0.02,"count":45,"countText":"剩余 0份","describe":"全发顺丰","endTime":1547816399,"id":1003,"imageUrl":"http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg","memo":"","price":0.01,"rec":true,"shopName":"美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方","status":2,"statusText":"已拍完","time":0,"title":"抢拍5","total":45}]
         * name : 当前抢拍
         * status : 1
         * statusText : 进行中
         * testName : 2019-01-18 15:00:00-2019-01-18 20:59:59
         * time : 0
         */

        public int begin;
        public int end;
        public String name;
        public int status;
        public String statusText;
        public String testName;
        public int time;
        public List<ItemsBean> items;

        public static class ItemsBean implements MultiItemEntity {
            /**
             * beginTime : 1547794800
             * costPrice : 0.02
             * count : 0
             * countText : 剩余 50份
             * describe : 阿萨德噶噶大概
             * endTime : 1547816399
             * id : 1001
             * imageUrl : http://192.168.2.200/asset/file/upload/20181211/e30d15c8edfd5608.jpg
             * markPrice : 99.0
             * memo :
             * price : 0.01
             * rec : true
             * shopName : 美味人间湖北省武汉市武昌区楚河汉街总部国际1701店全国唯一一家官方
             * status : 1
             * statusText : 立即抢拍
             * time : 18531
             * title : 抢拍3
             * total : 50
             */

            public int beginTime;
            public double costPrice;
            public int count;
            public String countText;
            public String describe;
            public int endTime;
            public String id;
            public String imageUrl;
            public double markPrice;
            public String memo;
            public double price;
            public boolean rec;
            public String shopName;
            public int status;
            public String statusText;
            public int time;
            public String title;
            public int total;

            @Override
            public int getItemType() {
                return QiangPaiListAdapter.TYPE_LEVEL_1;
            }
        }
    }
}
