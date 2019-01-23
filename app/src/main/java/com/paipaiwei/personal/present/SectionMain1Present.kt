package com.paipaiwei.personal.present

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.api.ProcessObserver2.constructor.gson
import com.yjhh.common.present.BasePresent
import com.yjhh.common.utils.LogUtils
import com.paipaiwei.personal.bean.Main1FootBean
import com.paipaiwei.personal.bean.Main1HeadBean
import com.paipaiwei.personal.bean.MainFinalDataBean
import com.paipaiwei.personal.model.SectionMainModel
import com.paipaiwei.personal.view.Main1View
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject

class SectionMain1Present(context: Context) : BasePresent() {

    lateinit var main1View: Main1View

    constructor(context: Context, main1View: Main1View) : this(context) {
        this.main1View = main1View
    }

    val sectionMainModel = SectionMainModel()

    fun homeIndex() { //单独获取首页1banner和tab
        toSubscribe2(sectionMainModel.homeIndex(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                LogUtils.i("homeIndex", response)
                val main1HeadBean = gson.fromJson<Main1HeadBean>(response, Main1HeadBean::class.java)
                // main1View.onSuccess(main1HeadBean)


            }

            override fun onFault(message: String) {
                LogUtils.i("homeIndex", message)
                // main1View.onFault(message)
            }

        })
    }


    fun recProduct(pageIndex: Int, pageSize: Int) { //单独获取首页1推荐
        toSubscribe2(sectionMainModel.recProduct(pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                LogUtils.i("recProduct", response)
                val main1HeadBean = gson.fromJson<Main1HeadBean>(response, Main1HeadBean::class.java)
                //main1View.onSuccess(main1HeadBean)

            }

            override fun onFault(message: String) {
                LogUtils.i("recProduct", message)
                //main1View.onFault(message)
            }

        })
    }


    fun joinMain(pageIndex: Int, pageSize: Int, flag: String) {


        val homeIndex = sectionMainModel.homeIndex()
        val recProduct = sectionMainModel.recProduct(pageIndex, pageSize)

        val disposable = Observable.zip(homeIndex, recProduct,
            BiFunction<ResponseBody, ResponseBody, MainFinalDataBean> { t1, t2 ->
                val jsonValue1 = t1.string()
                val mainFinalDataBean = MainFinalDataBean()
                if (!TextUtils.isEmpty(jsonValue1) && jsonValue1.contains("success")) {
                    val jSONObject1 = JSONObject(jsonValue1)
                    if (jSONObject1.getBoolean("success")) {
                        val jsonString = jSONObject1.getString("data")

                        val main1HeadBeans = gson.fromJson<Main1HeadBean>(jsonString, Main1HeadBean::class.java)

                        mainFinalDataBean.main1HeadBean = main1HeadBeans
                    } else {
                        mainFinalDataBean.main1HeadBean = Main1HeadBean()
                    }

                } else {
                    mainFinalDataBean.main1HeadBean = Main1HeadBean()
                }

                val jsonValue2 = t2.string()
                if (!TextUtils.isEmpty(jsonValue2) && jsonValue1.contains("success")) {
                    val jSONObject2 = JSONObject(jsonValue2)

                    LogUtils.i("joinMain", jsonValue2)
                    if (jSONObject2.getBoolean("success")) {
                        val jsonString = jSONObject2.getString("data")

                        val main1FootBeans = gson.fromJson<Main1FootBean>(jsonString, Main1FootBean::class.java)

                        mainFinalDataBean.main1FootBean = main1FootBeans


                    } else {
                        mainFinalDataBean.main1FootBean = Main1FootBean()
                    }

                } else {
                    mainFinalDataBean.main1FootBean = Main1FootBean()
                }
                mainFinalDataBean
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it != null) {
                        main1View.onSuccess(it, flag)
                    } else {
                        main1View.onFault("无法获取首页数据")
                    }
                }, {
                    main1View.onFault(it.toString())
                }
            )

    }


}