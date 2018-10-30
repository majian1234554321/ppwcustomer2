package com.yjhh.ppwcustomer;


import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yjhh.ppwcustomer.bean.ProvinceBean;
import com.yjhh.ppwcustomer.bean.ProvinceBean2;
import com.yjhh.ppwcustomer.common.utils.GetJsonDataUtil;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CurrentApplication extends TinkerApplication {


  public  static ProvinceBean2 [] provinceBean;

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


        Observable.just("1")
                .flatMap(new Function<String, ObservableSource<ProvinceBean2 []>>() {
                    @Override
                    public ObservableSource<ProvinceBean2 [] > apply(String s) throws Exception {

                        Gson gson = new Gson();
                        String JsonData = new GetJsonDataUtil().getJson(mAppInstance, "province.json");

                        Log.i("CurrentApplication", JsonData);
                         provinceBean = gson.fromJson(JsonData, ProvinceBean2 [].class);








                        return Observable.just(provinceBean );
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProvinceBean2 []>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProvinceBean2 [] s) {

                        Log.i("CurrentApplication", s.length+""+s[0].getName()+s[0].getNode().get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("CurrentApplication", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public static CurrentApplication getInstance() {
        return mAppInstance;
    }
}
