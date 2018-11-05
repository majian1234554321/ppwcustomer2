package com.yjhh.ppwcustomer.ui.fragment

import android.util.Log
import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.main2_2fragment.*

class Main2_2Fragment :BaseFragment() {


    override fun initView(rootView: View?) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutRes(): Int  = R.layout.main2_2fragment

    override fun initData() {
        Log.i("TAG","Main2_2Fragment")
        swipeLayout.autoRefresh()
    }

}