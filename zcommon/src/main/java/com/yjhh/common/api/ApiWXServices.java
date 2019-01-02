package com.yjhh.common.api;


import android.util.Log;
import android.webkit.WebSettings;
import androidx.annotation.NonNull;
import com.yjhh.common.BaseApplication;
import com.yjhh.common.Constants;
import com.yjhh.common.model.WxBean;
import com.yjhh.common.utils.APKVersionCodeUtils;
import com.yjhh.common.utils.Md5Util;
import com.yjhh.common.utils.SharedPreferencesUtils;
import io.reactivex.Observable;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class ApiWXServices {

    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    public static final String WXURL = "https://api.weixin.qq.com/sns/";


    private ApiWXServices() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        // 添加公共参数拦截器
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WXURL)
                .client(httpClient.build())
                .build();
    }

    private static class SingletonHolder {
        private static final ApiWXServices INSTANCE = new ApiWXServices();
    }


    public static ApiWXServices getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }




}
