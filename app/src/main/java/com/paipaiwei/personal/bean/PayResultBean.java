package com.paipaiwei.personal.bean;

import java.util.List;

public class PayResultBean {

    /**
     * commentId : 0
     * createdTime : 1547603618
     * disMoney : 0.0
     * id : 1370
     * ifComment : false
     * ifHasItem : 1
     * items : [{"id":0,"itemId":1000,"number":5,"price":0.01,"refundNumber":0,"status":0,"statusText":"未领取"}]
     * money : 0.05
     * number : 0
     * orderNo : 20190116095338016001
     * payStatus : 0
     * payStatusText : 支付失败
     * shopId : -1
     * shopLogoUrl :
     * status : 1
     * suppPayType : 3
     * times : 900
     * totalMoney : 0.05
     * type : 4
     * unDisMoney : 0.0
     * useDisMoney : 0
     * useMoney : 0
     * useStatus : 0
     * useTotalMoney : 0
     * useUnDisMoney : 0
     * userId : 1022
     */

    public int commentId;
    public int createdTime;
    public double disMoney;
    public int id;
    public boolean ifComment;
    public int ifHasItem;
    public float money;
    public int number;
    public String orderNo;
    public int payStatus;
    public String payStatusText;
    public int shopId;
    public String shopLogoUrl;
    public int status;
    public int suppPayType;
    public int times;
    public double totalMoney;
    public int type;
    public double unDisMoney;
    public int useDisMoney;
    public int useMoney;
    public int useStatus;
    public int useTotalMoney;
    public int useUnDisMoney;
    public int userId;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * id : 0
         * itemId : 1000
         * number : 5
         * price : 0.01
         * refundNumber : 0
         * status : 0
         * statusText : 未领取
         */

        public int id;
        public int itemId;
        public int number;
        public double price;
        public int refundNumber;
        public int status;
        public String statusText;
    }
}
