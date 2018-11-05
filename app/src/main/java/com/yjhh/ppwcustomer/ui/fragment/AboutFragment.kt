package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.aboutfragment.*

class AboutFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutRes(): Int = R.layout.aboutfragment

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_1 -> {
            }
            R.id.iev_2 -> {
            }
            R.id.iev_3 -> {
            }
            else -> {
            }
        }
    }


    override fun initView(rootView: View?) {
        iev_1.setOnClickListener(this)
        iev_2.setOnClickListener(this)
        iev_3.setOnClickListener(this)

    }
}