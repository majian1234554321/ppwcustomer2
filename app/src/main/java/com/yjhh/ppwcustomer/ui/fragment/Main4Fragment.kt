package com.yjhh.ppwcustomer.ui.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.NavigationOnClickListener
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.R.id.*
import com.yjhh.ppwcustomer.ui.activity.DisplayActivity
import kotlinx.android.synthetic.main.main4fragment.*
import kotlinx.android.synthetic.main.registfragment.*

class Main4Fragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_address -> {

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()

            }


            R.id.iev_message -> {

            }

            R.id.iev_browse -> {

            }
            R.id.iev_about -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "AboutFragment")
                    .withInt("age", 23)
                    .navigation()
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


            else -> {

            }
        }
    }

    override fun initView(rootView: View?) {

        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

        }else{
            tv_name.text = "未登录"
        }



        RxBus.default.toFlowable()

        iev_about.setOnClickListener(this)
        iev_browse.setOnClickListener(this)
        iev_message.setOnClickListener(this)

        tv_address.setOnClickListener(this)

        tv_name.setOnClickListener(this)
        profile_image.setOnClickListener(this)
        iv_setting.setOnClickListener(this)
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment
}