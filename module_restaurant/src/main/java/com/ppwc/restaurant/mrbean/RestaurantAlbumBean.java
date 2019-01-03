package com.ppwc.restaurant.mrbean;

import java.util.List;

public class RestaurantAlbumBean {

    /**
     * items : [{"id":1082,"imageUrl":"http://192.168.2.200:8080/file/upload/20181227/11e391340de98c7e.png","orderBy":1},{"id":1081,"imageUrl":"http://192.168.2.200:8080/file/upload/20181227/11e391340de98c7e.png","name":"","orderBy":1},{"id":1080,"imageUrl":"http://192.168.2.200:8080/file/upload/20181227/11e391340de98c7e.png","name":"少时诵诗书所","orderBy":0}]
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
         * id : 1082
         * imageUrl : http://192.168.2.200:8080/file/upload/20181227/11e391340de98c7e.png
         * orderBy : 1
         * name :
         */

        public int id;
        public String imageUrl;
        public int orderBy;
        public String name;
    }
}
