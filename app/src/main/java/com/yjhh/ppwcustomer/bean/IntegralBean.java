package com.yjhh.ppwcustomer.bean;

import java.util.List;

public class IntegralBean {


    /**
     * items : [{"balance":998,"createdTime":1541133131,"money":998,"remark":"注册成功，赠送998","tradeType":1001,"tradeTypeName":"积分兑换","type":1},{"balance":998,"createdTime":1541133131,"money":998,"remark":"充值成功","tradeType":1,"tradeTypeName":"充值","type":0}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 3
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
         * balance : 998.0
         * createdTime : 1541133131
         * money : 998.0
         * remark : 注册成功，赠送998
         * tradeType : 1001
         * tradeTypeName : 积分兑换
         * type : 1
         */

        public double balance;
        public int createdTime;
        public double money;
        public String remark;
        public int tradeType;
        public String tradeTypeName;
        public int type;
    }
}
