package com.yjhh.common.api;


import android.support.annotation.NonNull;
import com.yjhh.common.Constants;

import com.yjhh.common.BaseApplication;
import com.yjhh.common.utils.SharedPreferencesUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiServices {

    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;

    private ApiServices() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        // 添加公共参数拦截器
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("userAgent", "PPW_App")
                                .header("X-Requested-With", "XMLHttpRequest")
                                .header("PPW-TERMINAL", "1")
                                .header("JSESSIONID", String.valueOf(SharedPreferencesUtils.getParam(BaseApplication.context, "sessionId", "-1")))
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });


        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .build();
    }

    private static class SingletonHolder {
        private static final ApiServices INSTANCE = new ApiServices();
    }


    public static ApiServices getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


}
