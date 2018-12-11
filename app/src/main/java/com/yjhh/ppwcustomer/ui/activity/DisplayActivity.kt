package com.yjhh.ppwcustomer.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SystemBarUtil

import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.rxbusbean.RxAddressBean
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


    @Autowired
    @JvmField
    var bean: RxAddressBean? = null

    //var fragments: BaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        SystemBarUtil.tintStatusBar(this, ContextCompat.getColor(this, com.yjhh.common.R.color.colorPrimary), 1.0f);
        frameLayout.setPadding(0, getStatusBarHeight(this), 0, 0)


        displayTab =   intent.getStringExtra("displayTab")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragments: BaseFragment? = null
        when (displayTab) {

            "RegistFragment" -> {
                fragments = RegistFragment()
            }

            "ResetPasswordFragment" -> {
                fragments = ResetPasswordFragment()
            }

            "ForgotPasswordFragment" -> {
                fragments = ForgotPasswordFragment()
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

            "CollectionFragment" -> {
                fragments = CollFootFragment()

            }

            "MyMessageFragment" -> {
                fragments = MyMessageFragment()

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
