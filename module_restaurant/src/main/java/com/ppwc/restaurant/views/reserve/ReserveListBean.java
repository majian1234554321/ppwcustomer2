package com.ppwc.restaurant.views.reserve;

import java.util.List;

public class ReserveListBean {

    /**
     * items : [{"genderText":"先生","id":1048,"remark":"12566","shopId":1040,"shopName":"民生银行","status":4,"statusDisplayText":"已取消预约","statusText":"超时取消","time":1550592000,"timeText":"2019-02-20 00:00到店","timeTotal":0,"userCount":1,"userCountText":"1 位","userId":1022,"userMobile":"13800138000","userName":"1235666"},{"genderText":"先生","id":1049,"remark":"123456","shopId":1006,"shopName":"休息休息","status":4,"statusDisplayText":"已取消预约","statusText":"超时取消","time":1550586600,"timeText":"2019-02-19 22:30到店","timeTotal":0,"userCount":1,"userCountText":"1 位","userId":1022,"userMobile":"13800138000","userName":"那件"}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 2
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
         * genderText : 先生
         * id : 1048
         * remark : 12566
         * shopId : 1040
         * shopName : 民生银行
         * status : 4
         * statusDisplayText : 已取消预约
         * statusText : 超时取消
         * time : 1550592000
         * timeText : 2019-02-20 00:00到店
         * timeTotal : 0
         * userCount : 1
         * userCountText : 1 位
         * userId : 1022
         * userMobile : 13800138000
         * userName : 1235666
         */

        public String genderText;
        public String id;
        public String remark;
        public int shopId;
        public String shopName;
        public int status;
        public String statusDisplayText;
        public String statusText;
        public int time;
        public String timeText;
        public int timeTotal;
        public int userCount;
        public String userCountText;
        public int userId;
        public String userMobile;
        public String userName;
    }
}
