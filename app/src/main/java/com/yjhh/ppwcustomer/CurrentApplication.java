package com.yjhh.ppwcustomer;


import com.alibaba.android.arouter.launcher.ARouter;
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
                // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(mAppInstance); // 尽可能早，推荐在Application中初始化

    }
    public static CurrentApplication getInstance() {
        return mAppInstance;
    }
}
