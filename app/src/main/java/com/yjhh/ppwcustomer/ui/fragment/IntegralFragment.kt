package com.yjhh.ppwcustomer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.IntegralAdapter
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.MyMessageBean
import com.yjhh.ppwcustomer.present.SectionUselessPresent
import com.yjhh.ppwcustomer.view.MyMessageView
import kotlinx.android.synthetic.main.integralfragment.*

class IntegralFragment : BaseFragment(), MyMessageView {
    override fun onFault(errorMsg: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(main1bean: MyMessageBean, flag: String) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var startindex = 0
    val pageSize = 10
    var type = ""  // 类别，默认null（null/-1 全部 0余额 1积分）

    override fun getLayoutRes(): Int = R.layout.integralfragment


    var present: SectionUselessPresent? = null
    override fun initView() {
         present = SectionUselessPresent(mActivity,this)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        var mAdapter = IntegralAdapter()

        mRecyclerView.adapter = mAdapter
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()
    }


    private fun refresh() {

        startindex = 0
        present?.useraccountjoin(type, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        present?.useraccount(type, startindex, pageSize, "load")

    }


}
