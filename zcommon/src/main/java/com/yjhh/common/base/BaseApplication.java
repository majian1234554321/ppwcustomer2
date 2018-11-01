package com.yjhh.common.base;

import android.content.Context;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yjhh.common.utils.SharedPreferencesUtils;

import java.util.logging.Logger;

public class BaseApplication extends TinkerApplication {

    private static BaseApplication sInstance;

    public static Context context;

    public BaseApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.yjhh.common.CurrentApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }


    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        sInstance = this;
        Log.i("BaseApplication", String.valueOf(SharedPreferencesUtils.getParam(BaseApplication.context, "sessionId", "-1")));



    }

}
