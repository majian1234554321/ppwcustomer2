package com.paipaiwei.personal.bean;

import java.util.List;

public class Main1HeadBean {


    /**
     * banners : [{"ifAdv":false,"imageUrl":"http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg","title":"gggg"},{"ifAdv":false,"imageUrl":"http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg","title":"gggg"},{"ifAdv":false,"imageUrl":"http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg","title":"gggg"},{"ifAdv":false,"imageUrl":"http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg","title":"gggg"},{"ifAdv":false,"imageUrl":"http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg","title":"gggg"}]
     * jinli : {"imageUrl":"http://192.168.2.200/asset/file/upload/20181222/b4ea95c45258e2b3.jpg","linkUrl":"http://192.168.2.200:8080/activity/jinli","status":1,"title":"12月锦鲤活动"}
     * qiangPais : [{"beginTime":1546927200,"count":12,"countText":"剩余 28份","endTime":1546937999,"id":1002,"imageUrl":"http://192.168.2.200/asset/file/upload/20181228/3e3a06f7c0e3d6d6.png","memo":"","rec":false,"status":1,"statusText":"立即抢拍","title":"抱","total":40},{"beginTime":1546927200,"count":15,"countText":"剩余 25份","endTime":1546937999,"id":1001,"imageUrl":"http://192.168.2.200/asset/file/upload/20181227/9bdf1dd17083c8f5.png","memo":"","rec":false,"status":1,"statusText":"立即抢拍","title":"其他","total":40},{"beginTime":1546927200,"count":0,"countText":"剩余 40份","endTime":1546937999,"id":1000,"imageUrl":"http://192.168.2.200/asset/file/upload/20181227/beeb280f2b7f05f2.png","memo":"","rec":true,"status":1,"statusText":"立即抢拍","title":"明志","total":40}]
     * tabs : [{"code":"","iconUrl":"http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png","ifBuild":false,"ifHideText":false,"linkUrl":"01","rowIndex":0,"text":"美食"},{"code":"","iconUrl":"http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png","ifBuild":false,"ifHideText":false,"linkUrl":"02","rowIndex":0,"text":"休闲娱乐"},{"code":"","iconUrl":"http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png","ifBuild":false,"ifHideText":false,"linkUrl":"03","rowIndex":0,"text":"医疗美容"},{"code":"","iconUrl":"http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png","ifBuild":false,"ifHideText":false,"linkUrl":"04","rowIndex":0,"text":"丽人美发"},{"code":"","iconUrl":"http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png","ifBuild":false,"ifHideText":false,"linkUrl":"05","rowIndex":0,"text":"酒店住宿"}]
     * youXuanText : 为你优选
     */

    public JinliBean jinli;
    public String youXuanText;
    public List<BannersBean> banners;
    public List<QiangPaisBean> qiangPais;
    public List<TabsBean> tabs;

    public static class JinliBean {
        /**
         * imageUrl : http://192.168.2.200/asset/file/upload/20181222/b4ea95c45258e2b3.jpg
         * linkUrl : http://192.168.2.200:8080/activity/jinli
         * status : 1
         * title : 12月锦鲤活动
         */

        public String imageUrl;
        public String linkUrl;
        public int status;
        public String title;
    }

    public static class BannersBean {
        /**
         * ifAdv : false
         * imageUrl : http://192.168.2.200/asset/file/upload/20190103/a775ab803c1386e5.jpeg
         * title : gggg
         */

        public boolean ifAdv;
        public String imageUrl;
        public String title;
        public String linkUrl;
    }

    public static class QiangPaisBean {
        /**
         * beginTime : 1546927200
         * count : 12
         * countText : 剩余 28份
         * endTime : 1546937999
         * id : 1002
         * imageUrl : http://192.168.2.200/asset/file/upload/20181228/3e3a06f7c0e3d6d6.png
         * memo :
         * rec : false
         * status : 1
         * statusText : 立即抢拍
         * title : 抱
         * total : 40
         */

        public int time;
        public int beginTime;
        public int count;
        public String countText;
        public int endTime;
        public String id;
        public String imageUrl;
        public String memo;
        public boolean rec;
        public int status;
        public String statusText;
        public String title;
        public int total;
    }

    public static class TabsBean {
        /**
         * code :
         * iconUrl : http://192.168.2.200/asset/file/upload/20181227/d166130fa6c0d6fa.png
         * ifBuild : false
         * ifHideText : false
         * linkUrl : 01
         * rowIndex : 0
         * text : 美食
         */

        public String code;
        public String iconUrl;
        public boolean ifBuild;
        public boolean ifHideText;
        public String linkUrl;
        public int rowIndex;
        public String text;
    }
}
