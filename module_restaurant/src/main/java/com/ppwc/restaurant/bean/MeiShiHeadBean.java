package com.ppwc.restaurant.bean;

import java.util.List;

public class MeiShiHeadBean {

    /**
     * bannerModels : [{"imageUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg"},{"imageUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg"},{"imageUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg"}]
     * queryAll : {"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"全部（999）","value":""},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"4"}],"title":"全部","value":""}
     * queryAuto : {"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"智能排序"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"占位 1","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"占位 2","value":"2"}],"title":"智能排序"}
     * queryOne : {"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"一元拍","value":"1"}
     * querySearch : {"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位1","value":"0"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位2","value":"1"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位3","value":"2"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位4","value":"3"}],"title":"筛选"}
     * tabsModuleModels : [{"iconUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg","ifBuild":false,"ifHideText":false,"rowIndex":0,"text":"占位"},{"iconUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg","ifBuild":false,"ifHideText":false,"rowIndex":0,"text":"占位"},{"iconUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg","ifBuild":false,"ifHideText":false,"rowIndex":0,"text":"占位"},{"iconUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg","ifBuild":false,"ifHideText":false,"rowIndex":0,"text":"占位"},{"iconUrl":"http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg","ifBuild":false,"ifHideText":false,"rowIndex":0,"text":"占位"}]
     */

    public QueryAllBean queryAll;
    public QueryAutoBean queryAuto;
    public QueryOneBean queryOne;
    public QuerySearchBean querySearch;
    public List<BannerModelsBean> bannerModels;
    public List<TabsModuleModelsBean> tabsModuleModels;

    public static class QueryAllBean {
        /**
         * ifHasNodes : true
         * ifMultiple : false
         * nodes : [{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"全部（999）","value":""},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"名称（888）","value":"4"}]
         * title : 全部
         * value :
         */

        public boolean ifHasNodes;
        public boolean ifMultiple;
        public String title;
        public String value;
        public List<NodesBean> nodes;

        public static class NodesBean {
            /**
             * ifHasNodes : false
             * ifMultiple : false
             * nodes : []
             * title : 全部（999）
             * value :
             */

            public boolean ifHasNodes;
            public boolean ifMultiple;
            public String title;
            public String value;
            public List<?> nodes;
        }
    }

    public static class QueryAutoBean {
        /**
         * ifHasNodes : true
         * ifMultiple : false
         * nodes : [{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"智能排序"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"占位 1","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"占位 2","value":"2"}]
         * title : 智能排序
         */

        public boolean ifHasNodes;
        public boolean ifMultiple;
        public String title;
        public List<NodesBeanX> nodes;

        public static class NodesBeanX {
            /**
             * ifHasNodes : false
             * ifMultiple : false
             * nodes : []
             * title : 智能排序
             * value : 1
             */

            public boolean ifHasNodes;
            public boolean ifMultiple;
            public String title;
            public String value;
            public List<?> nodes;
        }
    }

    public static class QueryOneBean {
        /**
         * ifHasNodes : false
         * ifMultiple : false
         * nodes : []
         * title : 一元拍
         * value : 1
         */

        public boolean ifHasNodes;
        public boolean ifMultiple;
        public String title;
        public String value;
        public List<?> nodes;
    }

    public static class QuerySearchBean {
        /**
         * ifHasNodes : true
         * ifMultiple : false
         * nodes : [{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位1","value":"0"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位2","value":"1"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位3","value":"2"},{"ifHasNodes":true,"ifMultiple":false,"nodes":[{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}],"title":"分组占位4","value":"3"}]
         * title : 筛选
         */

        public boolean ifHasNodes;
        public boolean ifMultiple;
        public String title;
        public List<NodesBeanXXX> nodes;

        public static class NodesBeanXXX {
            /**
             * ifHasNodes : true
             * ifMultiple : false
             * nodes : [{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位100","value":"1"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位101","value":"2"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位102","value":"3"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位103","value":"4"},{"ifHasNodes":false,"ifMultiple":false,"nodes":[],"title":"属性占位104","value":"5"}]
             * title : 分组占位1
             * value : 0
             */

            public boolean ifHasNodes;
            public boolean ifMultiple;
            public String title;
            public String value;
            public List<NodesBeanXX> nodes;

            public static class NodesBeanXX {
                /**
                 * ifHasNodes : false
                 * ifMultiple : false
                 * nodes : []
                 * title : 属性占位100
                 * value : 1
                 */

                public boolean confirmCheck;
                public boolean check;
                public boolean ifHasNodes;
                public boolean ifMultiple;
                public String title;
                public String value;
                public List<?> nodes;
            }
        }
    }

    public static class BannerModelsBean {
        /**
         * imageUrl : http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg
         */

        public String imageUrl;
    }

    public static class TabsModuleModelsBean {
        /**
         * iconUrl : http://192.168.2.200:8080/file/upload/20181225/03d5cdb46823593e.jpeg
         * ifBuild : false
         * ifHideText : false
         * rowIndex : 0
         * text : 占位
         */

        public String linkUrl;
        public String iconUrl;
        public boolean ifBuild;
        public boolean ifHideText;
        public int rowIndex;
        public String text;
    }
}
