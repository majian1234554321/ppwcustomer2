package com.paipaiwei.personal.ui.activity


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.view.RxView

import com.yjhh.common.base.BaseActivity
import com.paipaiwei.personal.R
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.ui.activity.login.LoginFragment
import com.paipaiwei.personal.ui.activity.onepay.PayResultFragment
import com.paipaiwei.personal.ui.activity.onepay.PayResultFragment2
import com.paipaiwei.personal.view.OrderView
import com.yjhh.common.bean.DisplayPayTypeBean
import com.yjhh.common.iview.PayView
import com.yjhh.common.model.WxPayBean

import com.yjhh.common.pay.RxPay
import com.yjhh.common.present.PayPresent
import com.yjhh.common.utils.APKVersionCodeUtils
import com.yjhh.common.utils.DateUtil
import com.yjhh.common.utils.RxCountDown
import kotlinx.android.synthetic.main.activity_pay.*
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import java.util.concurrent.TimeUnit

@Route(path = "/PayActivity/pay")
class PayActivity : BaseActivity() {
    @Autowired
    @JvmField
    var jsonValue: String? = null
    @Autowired
    @JvmField
    var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_pay)
        if (findFragment(LoginFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, PayFragment.newInstance(jsonValue,type))
        }

    }


    override fun onBackPressedSupport() {

        super.onBackPressedSupport()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {

        return DefaultHorizontalAnimator()
    }


}
