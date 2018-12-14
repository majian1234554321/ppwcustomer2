package com.paipaiwei.takeout_personal.bean.rxbusbean;

import java.io.Serializable;

public class RxAddressBean implements Serializable {
    public String flag; //ADD OR DELETE OR UPDATE

    public String address;
    public int gender;
    public String id;
    public boolean ifDefault;
    public String tags;
    public String userName;
    public String userPhone;
    public String no;
    public int position;


    public RxAddressBean() {
    }
}
