package com.yjhh.ppwcustomer.bean;

import com.yjhh.ppwcustomer.common.extend.TypeFactory;
import com.yjhh.ppwcustomer.common.extend.Visitable;

import java.util.List;

public class Main1HeadBean {

    private List<BannersBean> banners;
    private List<ContentsBean> contents;
    private List<List<TabsBean>> tabs;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public List<List<TabsBean>> getTabs() {
        return tabs;
    }

    public void setTabs(List<List<TabsBean>> tabs) {
        this.tabs = tabs;
    }

    public static class BannersBean  {
        /**
         * imageUrl : http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png
         * linkUrl : http://www.hbyjhh.com
         * orderBy : 0
         * title : 测试
         */



        private String imageUrl;
        private String linkUrl;
        private int orderBy;
        private String title;

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

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ContentsBean  {
        /**
         * id : 0
         * isNews : false
         * isRec : false
         * linkUrl : https://www.baidu.com
         * publishTimes : 0
         * title : 今天天气不错
         * type : 0
         */




        private int id;
        private boolean isNews;
        private boolean isRec;
        private String linkUrl;
        private int publishTimes;
        private String title;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsNews() {
            return isNews;
        }

        public void setIsNews(boolean isNews) {
            this.isNews = isNews;
        }

        public boolean isIsRec() {
            return isRec;
        }

        public void setIsRec(boolean isRec) {
            this.isRec = isRec;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getPublishTimes() {
            return publishTimes;
        }

        public void setPublishTimes(int publishTimes) {
            this.publishTimes = publishTimes;
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

    public static class TabsBean {
        /**
         * iconUrl : http://192.168.2.200:8080/file/20181030/4da9c5571b3b02de.png
         * isAll : false
         * isBuild : false
         * isHideText : false
         * isHot : false
         * isSeckill : false
         * linkUrl : http://www.hbyjhh.com
         * rowIndex : 0
         * text : 测试
         * title : 测试
         */

        private String iconUrl;
        private boolean isAll;
        private boolean isBuild;
        private boolean isHideText;
        private boolean isHot;
        private boolean isSeckill;
        private String linkUrl;
        private int rowIndex;
        private String text;
        private String title;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public boolean isIsAll() {
            return isAll;
        }

        public void setIsAll(boolean isAll) {
            this.isAll = isAll;
        }

        public boolean isIsBuild() {
            return isBuild;
        }

        public void setIsBuild(boolean isBuild) {
            this.isBuild = isBuild;
        }

        public boolean isIsHideText() {
            return isHideText;
        }

        public void setIsHideText(boolean isHideText) {
            this.isHideText = isHideText;
        }

        public boolean isIsHot() {
            return isHot;
        }

        public void setIsHot(boolean isHot) {
            this.isHot = isHot;
        }

        public boolean isIsSeckill() {
            return isSeckill;
        }

        public void setIsSeckill(boolean isSeckill) {
            this.isSeckill = isSeckill;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
