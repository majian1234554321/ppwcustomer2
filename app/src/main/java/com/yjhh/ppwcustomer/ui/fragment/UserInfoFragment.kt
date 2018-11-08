package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.userinfofragment.*

class UserInfoFragment : BaseFragment(), View.OnClickListener {


    override fun getLayoutRes(): Int = R.layout.userinfofragment

    override fun initView() {
        iev_nickName.setOnClickListener(this)
        iev_birthday.setOnClickListener(this)
        iev_address.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        when (v?.id) {
            R.id.iev_nickName -> {
            }
            R.id.iev_birthday -> {
            }
            R.id.iev_address -> {
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

}