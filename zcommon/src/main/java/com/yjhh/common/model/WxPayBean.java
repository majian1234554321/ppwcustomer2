package com.yjhh.common.model;

public class WxPayBean {


    /**
     * id : 61
     * noncestr : 0087a5791168442db7ea401ac9c21296
     * packagestr : prepay_idwx0710122142548990e39b9d371575247588
     * partnerid : 1521874001
     * paysign : 341B44B37FCCA7FFA8CD19E6B59DF454
     * signtype : MD5
     * timestamp : 638916
     */

    public String id;
    public String noncestr;
    public String packagestr;
    public String partnerid;
    public String paysign;
    public String prepayid;
    public String signtype;
    public String timestamp;

    public WxPayBean(String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) {
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.packageValue = packageValue;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
    }

    public String appId;
    public String partnerId;
    public String prepayId;
    public String packageValue;
    public String nonceStr;
    public String timeStamp;
    public String sign;


}
