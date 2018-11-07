package com.yjhh.ppwcustomer.bean;

import java.util.List;

public class CouponBean {

    /**
     * items : [{"effectiveTime":1544677860000,"expiredTime":1576213860000,"minRang":399,"remark":"满399可用,立减35元。","subType":0,"title":"全部商品均可使用","type":0,"value":35}]
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 3
     */

    private QueryModelBean queryModel;
    private int recordCount;
    private List<ItemsBean> items;

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
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
         * effectiveTime : 1544677860000
         * expiredTime : 1576213860000
         * minRang : 399
         * remark : 满399可用,立减35元。
         * subType : 0
         * title : 全部商品均可使用
         * type : 0
         * value : 35
         */

        private long effectiveTime;
        private long expiredTime;
        private int minRang;
        private String remark;
        private int subType;
        private String title;
        private int type;
        private int value;

        public long getEffectiveTime() {
            return effectiveTime;
        }

        public void setEffectiveTime(long effectiveTime) {
            this.effectiveTime = effectiveTime;
        }

        public long getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(long expiredTime) {
            this.expiredTime = expiredTime;
        }

        public int getMinRang() {
            return minRang;
        }

        public void setMinRang(int minRang) {
            this.minRang = minRang;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSubType() {
            return subType;
        }

        public void setSubType(int subType) {
            this.subType = subType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
