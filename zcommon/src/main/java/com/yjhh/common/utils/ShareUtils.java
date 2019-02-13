package com.yjhh.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.yjhh.common.BaseApplication;
import com.yjhh.common.Constants;
import com.yjhh.common.R;
import com.yjhh.common.view.AbsSheetDialog;
import com.yjhh.common.view.AlertDialogFactory;
import com.yjhh.common.view.BottomHorSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;

import static com.yjhh.common.Constants.APP_ID_QQ;

public class ShareUtils {


    private ShareUtils() {
    }

    private static ShareUtils instance;


    public static ShareUtils getInstance() {
        synchronized (ShareUtils.class) {
            if (instance == null) {
                instance = new ShareUtils();
            }
        }
        return instance;
    }


    public static void dialog(final Activity activity, final IUiListener mIUiListener, final String title, final String webpageUrl, final String description) {


        AlertDialogFactory.createFactory(activity).getBottomHorDialog("分享",
                Arrays.asList(new BottomHorSheetDialog.Bean("QQ", R.drawable.share_qq),
                        new BottomHorSheetDialog.Bean("QQ空间", R.drawable.share_qzone),
                        new BottomHorSheetDialog.Bean("微信", R.drawable.share_wx),
                        new BottomHorSheetDialog.Bean("朋友圈", R.drawable.share_pyq),
                        new BottomHorSheetDialog.Bean("微博", R.drawable.share_sina)
                ),
                new AbsSheetDialog.OnItemClickListener<BottomHorSheetDialog.Bean>() {
                    @Override
                    public void onClick(Dialog dlg, int position, BottomHorSheetDialog.Bean item) {


                        switch (position) {
                            case 0:

                                final Tencent mTencent = Tencent.createInstance(APP_ID_QQ, BaseApplication.context);
                                final Bundle params = new Bundle();
                                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                                params.putString(QQShare.SHARE_TO_QQ_TITLE, title);// 标题
                                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
                                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, webpageUrl);// 内容地址
                                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
                                params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
                                //分享操作要在主线程中完成
                                mTencent.shareToQQ(activity, params, mIUiListener);

                                break;
                            case 1:
                                final Tencent mTencent2 = Tencent.createInstance(APP_ID_QQ, activity);
                                final Bundle params2 = new Bundle();
                                params2.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                                params2.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);// 标题
                                params2.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
                                params2.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, webpageUrl);// 内容地址
                                ArrayList<String> imgUrlList = new ArrayList<>();
                                imgUrlList.add("http://f.hiphotos.baidu.com/image/h%3D200/sign=6f05c5f929738bd4db21b531918a876c/6a600c338744ebf8affdde1bdef9d72a6059a702.jpg");
                                params2.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
                                // 分享操作要在主线程中完成
                                mTencent2.shareToQzone(activity, params2, mIUiListener);

                                break;
                            case 2:
                                initWx(activity, 0, title, webpageUrl, description);
                                break;
                            case 3:
                                initWx(activity, 1, title, webpageUrl, description);
                                break;
                            case 4:

                                break;

                        }

                    }

                    @Override
                    public void onCancel(Dialog dlg) {

                    }
                });
    }


//    public ShareUtils initWeiXin(Context context) {
//        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID_WX, true);
//        api.registerApp(Constants.APP_ID_WX);//将应用appid注册到微信
//        WXTextObject textObject = new WXTextObject();
//        textObject.text = "分享1212121";//内容
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = textObject;
//        msg.description = "内容描述";//描述
//
//        req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
//        req.message = msg;
//
//        return instance;
//
//    }


    /**
     * 分享消息到微信
     * type 0:分享到微信
     * type 1:分享到朋友圈
     */

    public static void initWx(Activity activity, int type, String title, String webpageUrl, String description) {

        IWXAPI api = WXAPIFactory.createWXAPI(activity, Constants.APP_ID_WX, true);
        api.registerApp(Constants.APP_ID_WX);//将应用appid注册到微信

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webpageUrl;
//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "拍拍味";
        msg.description = description;
        Bitmap thumbBmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.icon_image);
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

//构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());

        req.message = msg;


        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//加上这一段代码即未分享到朋友圈，否则分享到微信
        }
        api.sendReq(req);
    }


}
