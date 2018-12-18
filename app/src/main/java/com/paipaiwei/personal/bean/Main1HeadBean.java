package com.paipaiwei.personal.bean;

import java.util.List;

public class Main1HeadBean {


    private List<?> banners;
    private List<ContentsBean> contents;
    private List<TabsBean> tabs;

    public List<?> getBanners() {
        return banners;
    }

    public void setBanners(List<?> banners) {
        this.banners = banners;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public List<TabsBean> getTabs() {
        return tabs;
    }

    public void setTabs(List<TabsBean> tabs) {
        this.tabs = tabs;
    }

    public static class ContentsBean {
        /**
         * id : 0
         * ifNews : false
         * ifRec : false
         * linkUrl : https://www.baidu.com
         * title : 今天天气不错
         * type : 0
         */

        private int id;
        private boolean ifNews;
        private boolean ifRec;
        private String linkUrl;
        private String title;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIfNews() {
            return ifNews;
        }

        public void setIfNews(boolean ifNews) {
            this.ifNews = ifNews;
        }

        public boolean isIfRec() {
            return ifRec;
        }

        public void setIfRec(boolean ifRec) {
            this.ifRec = ifRec;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
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
         * ifAll : false
         * ifBuild : false
         * ifHideText : false
         * ifHot : false
         * ifSeckill : false
         * linkUrl : http://www.hbyjhh.com
         * rowIndex : 0
         * text : 测试
         */

        private String iconUrl;
        private boolean ifAll;
        private boolean ifBuild;
        private boolean ifHideText;
        private boolean ifHot;
        private boolean ifSeckill;
        private String linkUrl;
        private int rowIndex;
        private String text;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public boolean isIfAll() {
            return ifAll;
        }

        public void setIfAll(boolean ifAll) {
            this.ifAll = ifAll;
        }

        public boolean isIfBuild() {
            return ifBuild;
        }

        public void setIfBuild(boolean ifBuild) {
            this.ifBuild = ifBuild;
        }

        public boolean isIfHideText() {
            return ifHideText;
        }

        public void setIfHideText(boolean ifHideText) {
            this.ifHideText = ifHideText;
        }

        public boolean isIfHot() {
            return ifHot;
        }

        public void setIfHot(boolean ifHot) {
            this.ifHot = ifHot;
        }

        public boolean isIfSeckill() {
            return ifSeckill;
        }

        public void setIfSeckill(boolean ifSeckill) {
            this.ifSeckill = ifSeckill;
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
    }
}
