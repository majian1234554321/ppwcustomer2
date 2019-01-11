package com.paipaiwei.personal;


import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.yjhh.common.BaseApplication;
import com.paipaiwei.personal.bean.ProvinceBean2;
import com.paipaiwei.personal.common.utils.GetJsonDataUtil;
import com.yjhh.common.Constants;
import com.yjhh.common.utils.AmpLocationUtil;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CurrentApplication extends BaseApplication {


    public static ProvinceBean2[] provinceBean;


    private static CurrentApplication mAppInstance;





    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(mAppInstance); // 尽可能早，推荐在Application中初始化
        AmpLocationUtil.init(context);


        Observable.just("1")
                .flatMap(new Function<String, ObservableSource<ProvinceBean2[]>>() {
                    @Override
                    public ObservableSource<ProvinceBean2[]> apply(String s) throws Exception {

                        Gson gson = new Gson();
                        String JsonData = new GetJsonDataUtil().getJson(mAppInstance, "province.json");

                        Log.i("CurrentApplication", JsonData);
                        provinceBean = gson.fromJson(JsonData, ProvinceBean2[].class);

                        return Observable.just(provinceBean);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProvinceBean2[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProvinceBean2[] s) {

                        Log.i("CurrentApplication", s.length + "" + s[0].getName() + s[0].getNode().get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("CurrentApplication", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        AmpLocationUtil.getCurrentLocation(new AmpLocationUtil.MyLocationListener() {
            @Override
            public void result(AMapLocation it) {

                StringBuilder sb = new StringBuilder();

                if (it.getErrorCode() == 0) {
                    Log.e("LocationServer", "获取当前定位结果来源:::" + it.getLocationType());
                    Log.e("LocationServer", "获取纬度:::" + it.getLatitude());
                    Log.e("LocationServer", "获取经度:::" + it.getLongitude());
                    Log.e("LocationServer", "获取精度信息:::" + it.getAccuracy());
                    Log.e("LocationServer", "获取地址:::" + it.getAddress());
                    Log.e("LocationServer", "获取国家信息:::" + it.getCountry());
                    Log.e("LocationServer", "获取省信息:::" + it.getProvince());
                    Log.e("LocationServer", "获取城市信息:::" + it.getCity());
                    Log.e("LocationServer", "获取城区信息:::" + it.getDistrict());
                    Log.e("LocationServer", "获取街道信息:::" + it.getStreet());
                    Log.e("LocationServer", "获取街道门牌号信息:::" + it.getStreetNum());
                    Log.e("LocationServer", "获取城市编码:::" + it.getCityCode());
                    Log.e("LocationServer", "获取地区编码:::" + it.getAdCode());
                    Log.e("LocationServer", "获取当前定位点的AOI信息:::" + it.getAoiName());
                    Log.e("LocationServer", "获取当前室内定位的建筑物Id:::" + it.getBuildingId());
                    Log.e("LocationServer", "获取当前室内定位的楼层:::" + it.getFloor());
                    Log.e("LocationServer", "获取GPS的当前状态:::" + it.getGpsAccuracyStatus());
                    Log.e("LocationServer", "获取定位信息描述:::" + it.getLocationDetail());
                    Log.e("LocationServer", "获取方向角信息:::" + it.getBearing());
                    Log.e("LocationServer", "获取速度信息:::" + it.getSpeed() + "m/s");
                    Log.e("LocationServer", "获取海拔高度信息:::" + it.getAltitude());
                    Log.e("LocationServer", "获取当前位置的POI名称:::" + it.getPoiName());

                    Constants.district = it.getDistrict();
                    Constants.LATITUDE = String.valueOf(it.getLatitude());//经度
                    Constants.LONGITUDE = String.valueOf(it.getLongitude());
                    ;//维度

                    Constants.street = it.getStreet();


                } else {
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:").append(it.getErrorCode()).append("\n");
                    sb.append("错误信息:").append(it.getErrorInfo()).append("\n");
                    sb.append("错误描述:").append(it.getLocationDetail()).append("\n");
                }

                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(it.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                sb.append("* GPS状态：").append(it.getLocationQualityReport().getGPSStatus()).append("\n");

                sb.append("****************").append("\n");
                //定位之后的回调时间


                Log.i("LocationServer", sb.toString());


            }
        });


    }


    public static CurrentApplication getInstance() {
        return mAppInstance;
    }
}
