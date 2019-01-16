package com.paipaiwei.personal.bean;

import java.util.List;

public class MoreSectionBean {

    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * code : 01
         * iconUrl : http://192.168.2.200/asset/file/upload/20190111/34f292d1db4f4e12.jpg
         * nodes : [{"code":"0101","iconUrl":"","nodes":[],"title":"快餐小吃"},{"code":"0102","iconUrl":"","nodes":[],"title":"面包甜点"},{"code":"0103","iconUrl":"","nodes":[],"title":"休闲茶饮"},{"code":"0104","iconUrl":"","nodes":[],"title":"中餐地方菜"},{"code":"0105","iconUrl":"","nodes":[],"title":"异国料理"},{"code":"0106","iconUrl":"","nodes":[],"title":"火锅烧烤"},{"code":"0107","iconUrl":"","nodes":[],"title":"自助餐"}]
         * title : 美食
         */

        public String code;
        public String iconUrl;
        public String title;
        public List<NodesBean> nodes;

        public static class NodesBean {
            /**
             * code : 0101
             * iconUrl :
             * nodes : []
             * title : 快餐小吃
             */

            public String code;
            public String iconUrl;
            public String title;
            public List<?> nodes;
        }
    }
}
