package com.yjhh.ppwcustomer.ui.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SystemBarUtil

import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.ui.fragment.*

import kotlinx.android.synthetic.main.activity_display.*


@Route(path = "/DisplayActivity/Display")
class DisplayActivity : BaseActivity() {

    @Autowired
    @JvmField
    var displayTab: String? = null
    @Autowired
    @JvmField
    var age: Int? = 0

    //var fragments: BaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        SystemBarUtil.tintStatusBar(this, ContextCompat.getColor(this, com.yjhh.common.R.color.colorPrimary), 1.0f);
        frameLayout.setPadding(0, getStatusBarHeight(this), 0, 0)

        when (displayTab) {
            "RegistFragment" -> {
                val fragments = RegistFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }

            "ResetPasswordFragment" -> {
                val fragments = ResetPasswordFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }

            "ForgotPasswordFragment" -> {
                val fragments = ForgotPasswordFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }

            "MyAddressFragment" -> {
                val fragments = MyAddressFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }


            "AboutFragment" -> {
                val fragments = AboutFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }

            "SettingFragment" -> {
                val fragments = SettingFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragments).commit()
            }

            else -> {

            }
        }


    }
}
