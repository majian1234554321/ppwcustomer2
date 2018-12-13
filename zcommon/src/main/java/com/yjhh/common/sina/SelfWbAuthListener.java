package com.yjhh.common.sina;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.yjhh.common.R;

public class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {

    public Activity activity;

    public SelfWbAuthListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(final Oauth2AccessToken token) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (token.isSessionValid()) {

                    AccessTokenKeeper.writeAccessToken(activity, token);
                    Toast.makeText(activity,
                            "OKOKOKOK", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void cancel() {
        Toast.makeText(activity,
                "微博登录失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(WbConnectErrorMessage errorMessage) {
        Toast.makeText(activity, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
    }
}