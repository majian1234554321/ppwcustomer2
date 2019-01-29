package com.ppwc.restaurant.apis

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ShopService {

    @FormUrlEncoded
    @POST("shop")
    fun shop(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商家首页


    @FormUrlEncoded
    @POST("shop/images")
    fun images(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商家首页


    @FormUrlEncoded
    @POST("shop/products")
    fun products(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商家首页

    @FormUrlEncoded
    @POST("product/detail")
    fun product(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商品详情



}