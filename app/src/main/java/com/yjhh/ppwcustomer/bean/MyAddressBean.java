package com.yjhh.ppwcustomer.bean;

import java.util.List;

public class MyAddressBean {


    /**
     * items : [{"address":"万达汉街总部国际","gender":0,"id":1001,"ifDefault":true,"tags":"家","userName":"联系人","userPhone":"17601386386"}]
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
         * address : 万达汉街总部国际
         * gender : 0
         * id : 1001
         * ifDefault : true
         * tags : 家
         * userName : 联系人
         * userPhone : 17601386386
         */

        public String address;
        public int gender;
        public String id;
        public boolean ifDefault;
        public String tags;
        public String userName;
        public String userPhone;
        public String no;
    }
}
