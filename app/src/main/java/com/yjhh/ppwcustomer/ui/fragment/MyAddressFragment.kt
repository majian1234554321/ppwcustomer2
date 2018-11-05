package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.NavigationOnClickListener
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.present.SectionUserPresent
import kotlinx.android.synthetic.main.myaddressfragment.*

class MyAddressFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.myaddressfragment

    override fun initView() {
        tbv_title.setOnNavigation(NavigationOnClickListener {
            activity?.finish()
        })


        val sectionUserPresent = SectionUserPresent(context)

//        sectionUserPresent.getAllUserAddress("","0","100")
//
//
//        sectionUserPresent.setNickName("666")
//
//        sectionUserPresent.getUseraddressDetail("10")
//        sectionUserPresent.getUserinfo()

        // sectionUserPresent.resetPassword("123456","654321","","0") //0原密码修改 1短信修改(只传入新密码)
        // sectionUserPresent.resetPassword("","123456","666666","1") //0原密码修改 1短信修改(只传入新密码)

    }
}
