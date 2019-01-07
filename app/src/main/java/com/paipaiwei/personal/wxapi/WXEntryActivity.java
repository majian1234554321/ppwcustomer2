package com.paipaiwei.personal.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelpay.PayResp;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.paipaiwei.personal.R;

import com.yjhh.common.Constants;
import com.yjhh.common.api.ApiServices;
import com.yjhh.common.api.ApiWXServices;
import com.yjhh.common.api.WXService;
import com.yjhh.common.model.WxBean;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Observable;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.entry);


        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID_WX, false);
        api.registerApp(Constants.APP_ID_WX);


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
        //  Toast.makeText(this, "openid = 请求" + req.openId + "请求类型：req.getType()", Toast.LENGTH_SHORT).show();

        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            case ConstantsAPI.COMMAND_LAUNCH_BY_WX:
                //Toast.makeText(this, R.string.launch_from_wx, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }


    @Override
    public void onResp(BaseResp resp) {


        // Log.i("WXEntryActivity","openid = 返回" + resp.openId + "返回类型：" + resp.getType() + "返回的code" + resp.errCode+"code = " + ((SendAuth.Resp) resp).code);


        switch (resp.getType()) {
            case 2: //分享
                if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                    finish();
                } else {
                    finish();
                }
                break;
            case 1: //登录
                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        //用户同意
                        String code = ((SendAuth.Resp) resp).code;
                        //通过code获取access_token
                        //https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.APP_ID_WX + "&secret=" + "87177d40f44845c742f6e75f58c3ca75" + "&code=" + code + "&grant_type=" + "authorization_code";

                        //通过access_token获取用户信息
                        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID

                        ApiWXServices.getInstance().create(WXService.class)
                                .access_token(Constants.APP_ID_WX, "87177d40f44845c742f6e75f58c3ca75", code, "authorization_code")
                                .flatMap(new Function<WxBean, ObservableSource<WxBean>>() {
                                    @Override
                                    public ObservableSource<WxBean> apply(WxBean wxBean) {
                                        return ApiWXServices.getInstance().create(WXService.class).userinfo(wxBean.access_token, wxBean.openid);
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<WxBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(WxBean wxBean) {
                                        Log.i("TAG", wxBean.toString());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });


                        //Log.i("WXEntryActivity", url);
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        //用户拒绝
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        //用户取消

                        finish();
                        break;
                    default:
                        break;
                }


                break;

        }


        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //result = R.string.errcode_deny;
                break;
            default:
                //result = R.string.errcode_unknown;
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private void goToGetMsg() {
		/*Intent intent = new Intent(this, GetFromWXActivity.class);
		intent.putExtras(getIntent());
		startActivity(intent);
		finish();*/
    }

    private void goToShowMsg(ShowMessageFromWX.Req showReq) {
		/*WXMediaMessage wxMsg = showReq.message;
		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
		
		StringBuffer msg = new StringBuffer();
		msg.append("description: ");
		msg.append(wxMsg.description);
		msg.append("\n");
		msg.append("extInfo: ");
		msg.append(obj.extInfo);
		msg.append("\n");
		msg.append("filePath: ");
		msg.append(obj.filePath);
		
		Intent intent = new Intent(this, ShowFromWXActivity.class);
		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
		startActivity(intent);
		finish();*/
    }
}