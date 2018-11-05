package com.yjhh.ppwcustomer.ui.fragment


import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment

import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.ppwcustomer.R

import kotlinx.android.synthetic.main.main4fragment.*


class Main4Fragment : BaseFragment(), View.OnClickListener {


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.iev_about -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MembershipCardFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.iev_browse -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "RecentlyBrowseFragment")
                        .withInt("age", 23)
                        .navigation(context)
                }
            }

            R.id.iev_message -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "MyMessageFragment")
                        .withInt("age", 23)
                        .navigation(context)
                }
            }

            R.id.tv_Collection -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "CollectionFragment")
                        .withInt("age", 23)
                        .navigation(context)
                }

            }

            R.id.tv_Integral -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "IntegralFragment")
                        .withInt("age", 23)
                        .navigation(context)
                }

            }

            R.id.tv_Coupon -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "CouponFragment")
                        .withInt("age", 23)
                        .navigation(context)
                } else {

                }

            }

            R.id.iv_setting -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "SettingFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.tv_name -> {

                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
//                    ARouter.getInstance()
//                        .build("/LoginActivity/Login")
//                        .withString("name", "老王")
//                        .withInt("age", 23)
//                        .navigation(context)
                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .withString("name", "老王")
                        .withInt("age", 23)
                        .navigation(context)
                }


            }

            R.id.profile_image -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .withString("name", "老王")
                        .withInt("age", 23)
                        .navigation(context)
                }

            }

            R.id.iev_address -> {

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()

            }

            R.id.iev_evaluate -> {

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()

            }

            R.id.iev_service -> {

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()

            }

            else -> {

            }
        }
    }

    override fun initView() {

        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

        } else {
            tv_name.text = "未登录"
        }

        var dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
        }

        compositeDisposable.add(dis)


        val list = arrayOf(
            iev_evaluate,
            iev_address,
            iev_service,
            iev_about,
            iev_browse,
            iev_message,
            tv_Collection,
            tv_Integral,
            tv_Coupon,
            iv_setting,
            tv_name,
            profile_image
        )

        list.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment
}