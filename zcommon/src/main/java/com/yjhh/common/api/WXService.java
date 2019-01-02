package com.yjhh.common.api;

import com.yjhh.common.model.WxBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WXService {
    @GET("oauth2/access_token")
    Observable<WxBean> access_token(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    @GET("userinfo")
    Observable<WxBean> userinfo(@Query("access_token") String access_token, @Query("openid") String openid);

}
