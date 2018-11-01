package com.yjhh.ppwcustomer.ui.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.NavigationOnClickListener
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.ppwcustomer.R
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


            R.id.tv_loginout -> {

                SharedPreferencesUtils.setParam(context, "mobile", "")
                SharedPreferencesUtils.setParam(context, "nickName", "")
                SharedPreferencesUtils.setParam(context, "sessionId", "")
                SharedPreferencesUtils.setParam(context, "type", "")

            }

            else -> {

            }
        }
    }

    override fun initView(rootView: View?) {


        tv_address.setOnClickListener(this)
        tv_loginout.setOnClickListener(this)
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment
}