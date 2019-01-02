package com.yjhh.common.model;

public class WxBean {

    /**
     * access_token : 17_ybV63mQFCAVL6MVkiTi1eesU_MF9CDEUarB2yHPxRYuzOoSOP5uTjtKiQcanjwWpvPelJo0LwIpWoyal-bAS731ehPkrWv-svhB9hVG0rNc
     * expires_in : 7200
     * refresh_token : 17_VzDForABI1BOefLkftm_EKHFcWc__CFn5PjIZIuF9nna1pjPxEj0fuT5dNcnMQSeQTYiv3rY0ReBuea2m71YGNrLX1NfaafGgOYBUNJpN4g
     * openid : oxwRy1mwAqHI02ofVJXjWJZvsv6g
     * scope : snsapi_userinfo
     * unionid : oFrvF1LTetH2yZPcPZ0fi1vnYrB8
     */

    public String access_token;
    public int expires_in;
    public String refresh_token;
    public String openid;
    public String scope;
    public String unionid;


    public String nickname;
    public String sex;
    public String province;
    public String city;
    public String country;
    public String headimgurl;


    @Override
    public String toString() {
        return "WxBean{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                '}';
    }
}
