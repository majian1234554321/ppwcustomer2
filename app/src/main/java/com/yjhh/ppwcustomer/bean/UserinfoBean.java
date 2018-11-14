package com.yjhh.ppwcustomer.bean;

public class UserinfoBean {

    /**
     * account : {"balance":0,"integral":0,"userId":1022}
     * authStatus : 0
     * avaterUrl : http://192.168.2.200:8080//file/20181030/4da9c5571b3b02de.png
     * bindWeChat : 0
     * garde : 0
     * id : 1022
     * identity : 0
     * identityName : 会员
     * mobile : 176****6386
     * nickName : 666
     * referees : {"avaterUrl":"http://192.168.2.200:8080//file/20181027/2b8be7a75bb9716d.jpg","mobile":"187****0000","nickName":"18758050000","userId":1018}
     * registerTime : 1540778901
     */

    public AccountBean account;
    public int authStatus;
    public String avaterUrl;
    public int bindWeChat;
    public int garde;
    public String birthday;
    public int id;
    public int identity;
    public String identityName;
    public String mobile;
    public String nickName;
    public RefereesBean referees;
    public int registerTime;

    public static class AccountBean {
        /**
         * balance : 0.0
         * integral : 0.0
         * userId : 1022
         */

        public double balance;
        public double integral;
        public int userId;
    }

    public static class RefereesBean {
        /**
         * avaterUrl : http://192.168.2.200:8080//file/20181027/2b8be7a75bb9716d.jpg
         * mobile : 187****0000
         * nickName : 18758050000
         * userId : 1018
         */

        public String avaterUrl;
        public String mobile;
        public String nickName;
        public int userId;
    }
}
