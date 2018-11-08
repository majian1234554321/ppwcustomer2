package com.yjhh.ppwcustomer.bean;

import java.util.List;

public class RecentlyBrowseBean {

    /**
     * items : [{"consumption":"人均45元","grade":4.9,"id":0,"ifTakeout":false,"imageUrl":"http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png","linkUrl":"http://www.baidu.com","remark":"好吃 回头客","subId":111,"time":1541655699,"title":"标题测试三生三世","type":0},{"consumption":"人均45元","grade":4.9,"id":1,"ifTakeout":false,"imageUrl":"http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png","linkUrl":"http://www.baidu.com","remark":"好吃 回头客","subId":111,"time":1541655699,"title":"标题测试三生三世","type":0},{"consumption":"人均45元","grade":4.9,"id":2,"ifTakeout":false,"imageUrl":"http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png","linkUrl":"http://www.baidu.com","remark":"好吃 回头客","subId":111,"time":1541655699,"title":"标题测试三生三世","type":0},{"consumption":"人均45元","grade":4.9,"id":3,"ifTakeout":false,"imageUrl":"http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png","linkUrl":"http://www.baidu.com","remark":"好吃 回头客","subId":111,"time":1541655699,"title":"标题测试三生三世","type":0},{"consumption":"人均45元","grade":4.9,"id":4,"ifTakeout":false,"imageUrl":"http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png","linkUrl":"http://www.baidu.com","remark":"好吃 回头客","subId":111,"time":1541655699,"title":"标题测试三生三世","type":0}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 5
     */

    private int pageCount;
    private QueryModelBean queryModel;
    private int recordCount;
    private List<ItemsBean> items;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

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
         * consumption : 人均45元
         * grade : 4.9
         * id : 0
         * ifTakeout : false
         * imageUrl : http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png
         * linkUrl : http://www.baidu.com
         * remark : 好吃 回头客
         * subId : 111
         * time : 1541655699
         * title : 标题测试三生三世
         * type : 0
         */

        private String consumption;
        private double grade;
        private int id;
        private boolean ifTakeout;
        private String imageUrl;
        private String linkUrl;
        private String remark;
        private int subId;
        private int time;
        private String title;
        private int type;

        public String getConsumption() {
            return consumption;
        }

        public void setConsumption(String consumption) {
            this.consumption = consumption;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIfTakeout() {
            return ifTakeout;
        }

        public void setIfTakeout(boolean ifTakeout) {
            this.ifTakeout = ifTakeout;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSubId() {
            return subId;
        }

        public void setSubId(int subId) {
            this.subId = subId;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
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
    }
}
