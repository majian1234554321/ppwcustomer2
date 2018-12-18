package com.paipaiwei.personal.bean;

import java.util.List;

public class MemCanBuyBean {

    /**
     * items : [{"id":1,"items":[{"id":1,"index":0,"price":18,"time":90,"timeText":"3个月"},{"id":2,"index":1,"price":99,"time":210,"timeText":"半年（6个月+1个月）"},{"id":3,"index":0,"price":998,"time":450,"timeText":"一年（12个月+3个月）"}],"name":"饿了么","remark":"吃饭喝茶不要钱","type":0,"typeName":"全品类卡"},{"id":2,"items":[{"id":11,"index":0,"price":9.9,"time":90,"timeText":"三个月"},{"id":1333,"index":1,"price":56.88,"time":210,"timeText":"半年卡"}],"name":"美食卡","type":11000,"typeName":"美食卡"}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 2
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
         * id : 1
         * items : [{"id":1,"index":0,"price":18,"time":90,"timeText":"3个月"},{"id":2,"index":1,"price":99,"time":210,"timeText":"半年（6个月+1个月）"},{"id":3,"index":0,"price":998,"time":450,"timeText":"一年（12个月+3个月）"}]
         * name : 饿了么
         * remark : 吃饭喝茶不要钱
         * type : 0
         * typeName : 全品类卡
         */

        public int id;
        public String name;
        public String remark;
        public String type;
        public String typeName;
        public List<ItemsBean> items;

        public static class ItemsBean {
            /**
             * id : 1
             * index : 0
             * price : 18
             * time : 90
             * timeText : 3个月
             */

            public String id;
            public String index;
            public double price;
            public String time;
            public String date;
            public String timeText;
        }
    }
}
