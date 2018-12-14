package com.paipaiwei.takeout_personal.bean;

import java.util.ArrayList;
import java.util.List;

public class Main1FootBean {

    /**
     * items : [{"beforePrice":45.22,"distance":"123km","id":0,"isBuy":false,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memberPrice":8.5,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":10000,"isBuy":false,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":20000,"isBuy":false,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memberPrice":8.5,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":30000,"isBuy":true,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":40000,"isBuy":true,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memberPrice":8.5,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":50000,"isBuy":true,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":60000,"isBuy":false,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memberPrice":8.5,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]},{"beforePrice":45.22,"distance":"123km","id":70000,"isBuy":false,"isCollect":false,"isMerRec":false,"isPlatRec":false,"memo":"提供免费wifi，免费停车","name":"正宗油焖大虾","price":13.22,"shopId":11111,"tags":["还可以"]}]
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 8
     */

    private QueryModelBean queryModel;
    private int recordCount;
    private ArrayList<ItemsBean> items;

    public QueryModelBean getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(QueryModelBean queryModel) {
        this.queryModel = queryModel;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public ArrayList<ItemsBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsBean> items) {
        this.items = items;
    }

    public static class QueryModelBean {
        /**
         * pageIndex : 0
         * pageSize : 10
         */

        private int pageIndex;
        private int pageSize;

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class ItemsBean {
        /**
         * beforePrice : 45.22
         * distance : 123km
         * id : 0
         * isBuy : false
         * isCollect : false
         * isMerRec : false
         * isPlatRec : false
         * memberPrice : 8.5
         * memo : 提供免费wifi，免费停车
         * name : 正宗油焖大虾
         * price : 13.22
         * shopId : 11111
         * tags : ["还可以"]
         */

        private double beforePrice;
        private String distance;
        private int id;
        private boolean isBuy;
        private boolean isCollect;
        private boolean isMerRec;
        private boolean isPlatRec;
        private double memberPrice;
        private String memo;
        private String name;
        private double price;
        private int shopId;
        private List<String> tags;

        public double getBeforePrice() {
            return beforePrice;
        }

        public void setBeforePrice(double beforePrice) {
            this.beforePrice = beforePrice;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsBuy() {
            return isBuy;
        }

        public void setIsBuy(boolean isBuy) {
            this.isBuy = isBuy;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public boolean isIsMerRec() {
            return isMerRec;
        }

        public void setIsMerRec(boolean isMerRec) {
            this.isMerRec = isMerRec;
        }

        public boolean isIsPlatRec() {
            return isPlatRec;
        }

        public void setIsPlatRec(boolean isPlatRec) {
            this.isPlatRec = isPlatRec;
        }

        public double getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(double memberPrice) {
            this.memberPrice = memberPrice;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
