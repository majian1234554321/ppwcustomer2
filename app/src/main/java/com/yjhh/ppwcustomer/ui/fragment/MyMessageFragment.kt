package com.yjhh.ppwcustomer.ui.fragment

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mm.opensdk.utils.Log
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.CouponFragmentAdapter
import com.yjhh.ppwcustomer.adapter.MyMessageFragmentAdapter
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.MyMessageBean
import com.yjhh.ppwcustomer.present.SectionCouponPresent
import com.yjhh.ppwcustomer.present.SectionOrderPresent
import com.yjhh.ppwcustomer.present.SectionUselessPresent
import com.yjhh.ppwcustomer.view.MyMessageView
import kotlinx.android.synthetic.main.mymessagefragment.*

class MyMessageFragment : BaseFragment(), MyMessageView {


    override fun getLayoutRes(): Int = R.layout.mymessagefragment

    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun onSuccess(main1bean: MyMessageBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.onRefresh(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)

        } else {
            mAdapter.onLoad(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var startindex = 0
    val pageSize = 10

    var share = ""


    lateinit var sectionCouponPresent: SectionUselessPresent
    lateinit var present: SectionOrderPresent

    var list = ArrayList<MyMessageBean.ItemsBean>()

    lateinit var mAdapter: MyMessageFragmentAdapter

    override fun initView() {


        mAdapter = MyMessageFragmentAdapter(list, context)
        sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)



        mRecyclerView.adapter = mAdapter


    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()

        startindex++
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }


    override fun initData() {
        Log.i("TAG", "Main2_1Fragment")

    }
}