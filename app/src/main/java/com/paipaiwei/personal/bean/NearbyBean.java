package com.paipaiwei.personal.bean;

import java.util.List;

public class NearbyBean {


    public List<BannerModelsBean> bannerModels;
    public List<CategoryModelsBean> categoryModels;

    public static class BannerModelsBean {
        /**
         * ifAdv : false
         * imageUrl : http://192.168.2.200/asset/file/upload/20181226/4ef48f0a7b4daa5d.png
         */

        public boolean ifAdv;
        public String imageUrl;
    }

    public static class CategoryModelsBean {
        /**
         * nodes : []
         * title : 全部
         * iconUrl : http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png
         * id : 0
         */

        public String title;
        public String iconUrl;
        public String code;
        public List<Nodes> nodes;
    }

    public static class Nodes {
        public String code;
        public String title;
    }

}
