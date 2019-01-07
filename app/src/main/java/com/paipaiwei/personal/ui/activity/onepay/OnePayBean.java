package com.paipaiwei.personal.ui.activity.onepay;

public class OnePayBean {

    /**
     * logoUrl : http://192.168.2.200/asset/file/upload/20181222/b4ea95c45258e2b3.jpg
     * propA : {"code":"pai-crystal","describe":"拍-水晶拍","id":1000,"name":"水晶拍","quantity":0}
     * propB : {"code":"pai-diamond","describe":"拍-钻石拍","id":1001,"name":"钻石拍","quantity":1}
     */

    public String logoUrl;
    public PropABean propA;
    public PropBBean propB;

    public static class PropABean {
        /**
         * code : pai-crystal
         * describe : 拍-水晶拍
         * id : 1000
         * name : 水晶拍
         * quantity : 0
         */

        public String code;
        public String describe;
        public String id;
        public String name;
        public String quantity;
    }

    public static class PropBBean {
        /**
         * code : pai-diamond
         * describe : 拍-钻石拍
         * id : 1001
         * name : 钻石拍
         * quantity : 1
         */

        public String code;
        public String describe;
        public int id;
        public String name;
        public int quantity;
    }
}
