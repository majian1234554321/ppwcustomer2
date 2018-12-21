package com.paipaiwei.personal.bean;

import java.util.List;

public class SignBean {

    /**
     * days : 0
     * daysSum : 0
     * daysTextHtml : 已连续签到 0 天
     * ifLogin : true
     * ifSign : false
     * items : [{"dateText":"16","iconCode":"miss-pai","order":1,"subIconCode":"liyu","type":2,"typeText":"漏签"},{"dateText":"17","iconCode":"miss","order":2,"subIconCode":"liyu","type":2,"typeText":"漏签"},{"dateText":"18","iconCode":"miss","order":3,"subIconCode":"liyu","type":2,"typeText":"漏签"},{"dateText":"今日","iconCode":"empty","order":4,"subIconCode":"liyu","type":0,"typeText":"未签"},{"dateText":"20","iconCode":"empty","order":5,"subIconCode":"liyu","type":0,"typeText":"未签"},{"dateText":"21","iconCode":"empty","order":6,"subIconCode":"liyu","type":0,"typeText":"未签"},{"dateText":"22","iconCode":"empty","order":7,"subIconCode":"liyu","type":0,"typeText":"未签"}]
     */

    public int days;
    public int daysSum;
    public String daysTextHtml;
    public boolean ifLogin;
    public boolean ifSign;
    public String rule;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * dateText : 16
         * iconCode : miss-pai
         * order : 1
         * subIconCode : liyu
         * type : 2
         * typeText : 漏签
         */

        public String dateText;
        public boolean ifJinLi;
        public int order;
        public boolean ifPai;
        public int type;
        public String typeText;
    }
}
