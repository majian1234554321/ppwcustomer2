package com.paipaiwei.personal.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import com.yjhh.common.Constants;
import com.yjhh.common.pay.PaymentStatus;
import com.yjhh.common.utils.RxBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID_WX);
        api.handleIntent(getIntent(), this);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        // Log.i("WXPayEntryActivity", "openid = 返回" + req.toString() + "返回类型：" + req.getType() );
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i("WXPayEntryActivity", "openid = 返回" + resp.errStr + "返回类型：" + resp.getType() + "返回的code" + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                //成功 展示成功页面
                RxBus.Companion.getDefault().post(new PaymentStatus(true));
            } else if (resp.errCode == -1) {
                //错误-1 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                RxBus.Companion.getDefault().post(new PaymentStatus(false));
            } else {
                //用户取消-2 无需处理。发生场景：用户不支付了，点击取消，返回APP。
                RxBus.Companion.getDefault().post(new PaymentStatus(false));
            }

        }

        finish();

    }
}