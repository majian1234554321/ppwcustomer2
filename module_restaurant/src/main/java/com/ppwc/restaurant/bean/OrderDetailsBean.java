package com.ppwc.restaurant.bean;

import java.util.List;

public class OrderDetailsBean {


    /**
     * commentId : 0
     * couponType : -1
     * createdTime : 1550730906
     * disMoney : 0.0
     * id : 1922
     * ifComment : false
     * ifHasItem : 1
     * items : [{"fromType":3,"id":0,"itemId":1060,"number":1,"price":0,"refundNumber":0,"status":1,"statusText":"待使用","title":"安卓测试-物品-001","useRemarks":["最低消费1","有效时间2019-02-18 - 2019-02-19","有效期内可用"],"value":"100.00"}]
     * money : 0.0
     * number : 0
     * orderNo : 20190221143500016001
     * payStatus : 1
     * payStatusText : 支付成功
     * payTime : 1550730906
     * payType : 0
     * shopId : 1009
     * shopLogoUrl : http://192.168.2.200/asset/file/upload/20181227/11e391340de98c7e.png?stmp=85862
     * shopName : 美味人间
     * status : 3
     * subStatus : 1
     * suppPayType : 3
     * times : 0
     * title : 安卓测试-物品-001
     * totalMoney : 0.0
     * type : 16
     * unDisMoney : 0.0
     * useStatus : 0
     * userId : 1022
     */

    public float useMoney;
    public float useUnDisMoney;
    public float useDisMoney;
    public float useTotalMoney;
    public String useTime;
    public String subStatus;
    public String couponId;
    public int commentId;
    public int couponType;
    public String createdTime;
    public double disMoney;
    public String id;
    public boolean ifComment;
    public int ifHasItem;
    public double money;
    public int number;
    public String orderNo;
    public int payStatus;
    public String payStatusText;
    public String payTime;
    public int payType;
    public String shopId;
    public String shopLogoUrl;
    public String shopName;
    public int status;

    public int suppPayType;
    public int times;
    public String title;
    public double totalMoney;
    public int type;
    public double unDisMoney;
    public int useStatus;
    public int userId;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * fromType : 3
         * id : 0
         * itemId : 1060
         * number : 1
         * price : 0.0
         * refundNumber : 0
         * status : 1
         * statusText : 待使用
         * title : 安卓测试-物品-001
         * useRemarks : ["最低消费1","有效时间2019-02-18 - 2019-02-19","有效期内可用"]
         * value : 100.00
         */

        public int fromType;
        public int id;
        public int itemId;
        public int number;
        public double price;
        public int refundNumber;
        public int status;
        public String statusText;
        public String title;
        public String value;
        public List<String> useRemarks;
    }
}
