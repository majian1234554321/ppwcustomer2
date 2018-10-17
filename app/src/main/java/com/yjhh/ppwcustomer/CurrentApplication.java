package com.yjhh.ppwcustomer;

import android.app.Application;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class CurrentApplication extends TinkerApplication {


    public CurrentApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.yjhh.ppwcustomer.CurrentApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }



    private static CurrentApplication mAppInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;

    }
    public static CurrentApplication getInstance() {
        return mAppInstance;
    }
}
