package com.yjhh.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.support.multidex.MultiDex;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yjhh.common.utils.SharedPreferencesUtils;

public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public static Context context;



    public static BaseApplication getIns() {
        return sInstance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getIns());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
        sInstance = this;
        Log.i("BaseApplication", String.valueOf(SharedPreferencesUtils.getParam(BaseApplication.context, "sessionId", "-1")));



    }

}
