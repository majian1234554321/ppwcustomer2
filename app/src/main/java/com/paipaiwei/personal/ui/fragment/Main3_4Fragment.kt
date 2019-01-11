package com.paipaiwei.personal.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R

import kotlinx.android.synthetic.main.main2_3fragment.*

class Main3_4Fragment : BaseFragment(), OnRefreshListener {
    override fun onRefresh(refreshLayout: RefreshLayout) {

    }


    override fun getLayoutRes(): Int = R.layout.main2_3fragment

    override fun initView() {

//        swipeLayout.setRefreshHeader(ClassicsHeader(context))
//        swipeLayout.setRefreshFooter(ClassicsFooter(context))
//        swipeLayout.setOnRefreshListener(this)
//
//        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        recyclerview.layoutManager = linearLayoutManager
//        //recyclerview.adapter = mAdapter


    }

    override fun initData() {
        Log.i("TAG", "Main2_4Fragment")
       // swipeLayout.autoRefresh()
    }
}