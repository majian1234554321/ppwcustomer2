package com.paipaiwei.personal.ui.fragment

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.common.utils.DataCleanManager
import kotlinx.android.synthetic.main.settingfragment.*

class SettingFragment : BaseFragment(), View.OnClickListener {


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_resetPwd -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "ResetPasswordFragment")
                    .withInt("age", 23)
                    .navigation()

            }
            R.id.iev_resetPhone -> {


                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "ChangeMobileFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.iev_resetMessage -> {

            }

            R.id.iev_resetMemory -> {
                DataCleanManager.clearAllCache(mActivity)
                iev_resetMemory.setTextContent(DataCleanManager.getTotalCacheSize(mActivity))
            }

            R.id.iev_resetVersion -> {

            }

            R.id.tv_loginout -> {
                loginOut()
                ARouter.getInstance()
                    .build("/LoginActivity/Login")
                    .withString("name", "老王")
                    .withInt("age", 23)
                    .navigation(context)

                RxBus.default.post(LoginBean("", false))

                activity?.finish()

            }

            else -> {
            }
        }
    }

    override fun initView() {
        iev_resetPwd.setOnClickListener(this)
        iev_resetPhone.setOnClickListener(this)
        iev_resetMessage.setOnClickListener(this)
        iev_resetMemory.setOnClickListener(this)
        iev_resetVersion.setOnClickListener(this)
        tv_loginout.setOnClickListener(this)

        iev_resetMemory.setTextContent(DataCleanManager.getTotalCacheSize(mActivity))




    }

    override fun getLayoutRes(): Int = R.layout.settingfragment


}