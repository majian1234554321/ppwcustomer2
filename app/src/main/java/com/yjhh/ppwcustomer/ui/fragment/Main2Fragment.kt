package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import android.support.v7.widget.LinearLayoutManager

import com.yjhh.ppwcustomer.common.extend.Visitable
import android.support.v7.widget.RecyclerView
import com.yjhh.ppwcustomer.adapter.MulitAdpter
import kotlinx.android.synthetic.main.main2fragment.*


class Main2Fragment:BaseFragment() {
    override fun initView(rootView: View?) {


//
//        //  模拟本地数据
//        val beans = ArrayList<Visitable>()
//        beans.add(
//            BannerBean(
//                "www.baidu.com"
//            )
//        )
//        beans.add(ContentBean())
//
//        beans.add(ContentBean())
//        val multiRecyclerAdapter = MulitAdpter(beans)
//        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        recyclerview.layoutManager = linearLayoutManager
//        recyclerview.setAdapter(multiRecyclerAdapter)

    }

    override fun getLayoutRes(): Int = R.layout.main2fragment
}