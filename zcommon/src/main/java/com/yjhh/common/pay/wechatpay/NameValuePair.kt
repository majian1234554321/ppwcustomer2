package com.yjhh.common.pay.wechatpay

/**
 * Created by cuieney on 2017/11/28.
 */

class NameValuePair(var name: String?, var value: String?) {

    override fun toString(): String {
        return "NameValuePair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}'
    }
}
