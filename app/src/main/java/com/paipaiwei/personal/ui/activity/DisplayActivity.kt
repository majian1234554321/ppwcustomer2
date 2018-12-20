package com.paipaiwei.personal.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SystemBarUtil

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.rxbusbean.RxAddressBean
import com.paipaiwei.personal.ui.activity.login.ForgotPasswordFragment
import com.paipaiwei.personal.ui.fragment.*

import kotlinx.android.synthetic.main.activity_display.*
import io.flutter.facade.Flutter


@Route(path = "/DisplayActivity/Display")
class DisplayActivity : BaseActivity() {

    @Autowired
    @JvmField
    var displayTab: String? = null

    @Autowired
    @JvmField
    var age: Int? = 0


    @Autowired
    @JvmField
    var bean: RxAddressBean? = null

    //var fragments: BaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)




        displayTab = intent.getStringExtra("displayTab")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragments: BaseFragment? = null
        when (displayTab) {

            "RegistFragment" -> {
                fragments = RegistFragment()
            }

            "ResetPasswordFragment" -> {
                fragments = ResetPassWordFragment()
            }


            "MyAddressFragment" -> {
                fragments = MyAddressFragment()

            }

            "AboutFragment" -> {
                fragments = AboutFragment()

            }

            "SettingFragment" -> {
                fragments = SettingFragment()

            }


            "RecentlyBrowseFragment" -> {
                fragments = RecentlyBrowseFragment()

            }

            "CouponFragment" -> {
                fragments = CouponFragment()

            }

            "MembershipCardFragment" -> {
                fragments = MembershipCardFragment()

            }


            "AddressADUFragment" -> {
                fragments = AddressADUFragment.newInstance(bean)

            }


            "SelectDistrictFragment" -> {
                fragments = SelectDistrictFragment()

            }

            "ChangeMobileFragment" -> {
                fragments = ChangeMobileFragment()

            }


            "IntegralFragment" -> {
                fragments = IntegralFragment()

            }


            else -> {

            }
        }


        if (fragments != null) {
            fragmentTransaction.replace(R.id.frameLayout, fragments).commit()
        }


    }
}