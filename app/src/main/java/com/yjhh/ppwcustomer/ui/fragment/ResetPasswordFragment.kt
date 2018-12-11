package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseFragment


import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.LoginBean
import com.yjhh.ppwcustomer.present.SectionUserPresent
import com.yjhh.ppwcustomer.view.RegistView


import kotlinx.android.synthetic.main.resetpasswordfragment.*

class ResetPasswordFragment : BaseFragment(), View.OnClickListener, RegistView {


    override fun registSuccess(date: LoginBean?) {

    }

    override fun registSuccess2(date: String?) {
        Toast.makeText(context, "密码修改成功,请重新登录", Toast.LENGTH_SHORT).show()

        loginOut()

        ARouter.getInstance()
            .build("/LoginActivity/Login")
            .withString("name", "老王")
            .withInt("age", 23)
            .navigation(context)
        activity?.finish()

    }



    override fun registFault(registFaultMessage: String) {
        Toast.makeText(context, "密码修改失败$registFaultMessage", Toast.LENGTH_SHORT).show()

    }

    override fun sendSMSSuccess(date: LoginBean?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败$message", Toast.LENGTH_SHORT).show()
    }

    val identity = "0"//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "21"//1登录 2注册 21 重置密码
    val refId = "";//推荐人ID/phone
    val MAX_COUNT_TIME = 5L

    override fun onClick(v: View?) {

        if (et_password.text.length >= 6 && et_password2.text.length >= 6 && et_password.text.toString() != et_password2.text.toString()) {
            val sectionUserPresent = SectionUserPresent(context, this)
            sectionUserPresent.resetPassword(et_password.text.toString(), et_password2.text.toString(), "", "0")
        } else {
            Toast.makeText(context, "密码不能大于6位并且新旧密码不能相等", Toast.LENGTH_LONG).show()
        }

    }

    override fun getLayoutRes(): Int = R.layout.resetpasswordfragment

    override fun initView() {

        bt_commit.setOnClickListener(this)



    }
}