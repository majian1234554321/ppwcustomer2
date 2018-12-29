package com.yjhh.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yjhh.common.Constants;
import com.yjhh.common.R;

public class ShareUtils {


    private ShareUtils() {
    }

    private static ShareUtils instance;
    public IWXAPI api;
    public SendMessageToWX.Req req;

    public static ShareUtils getInstance() {
        synchronized (ShareUtils.class) {
            if (instance == null) {
                instance = new ShareUtils();
            }
        }
        return instance;
    }


    public ShareUtils initWeiXin(Context context) {
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID_WX, true);
        api.registerApp(Constants.APP_ID_WX);//将应用appid注册到微信
        WXTextObject textObject = new WXTextObject();
        textObject.text = "分享1212121";//内容

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = "内容描述";//描述

        req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;

        return instance;

    }


    public ShareUtils initWx(Context context) {

        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID_WX, true);
        api.registerApp(Constants.APP_ID_WX);//将应用appid注册到微信

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.hbyjhh.com/";
//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "拍拍味";
        msg.description = "这不是饿了吗的广告,这是拍拍味的新零售广告。";
        Bitmap thumbBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_image);
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

//构造一个Req
        req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());

        req.message = msg;


        return instance;
    }


    /**
     * 分享消息到微信
     * type 0:分享到微信
     * type 1:分享到朋友圈
     */
    public void sendToWeiXin(int type) {
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//加上这一段代码即未分享到朋友圈，否则分享到微信
        }
        api.sendReq(req);
    }


}
