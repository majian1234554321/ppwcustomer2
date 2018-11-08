package com.yjhh.ppwcustomer.ui.fragment

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R

import com.yjhh.ppwcustomer.adapter.CouponFragmentAdapter
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.present.SectionCouponPresent
import com.yjhh.ppwcustomer.present.SectionOrderPresent
import com.yjhh.ppwcustomer.view.CouponView

import kotlinx.android.synthetic.main.couponfragment.*


class CouponFragment : BaseFragment(), CouponView {

    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun onSuccess(main1bean: CouponBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.onRefresh(main1bean.items as ArrayList<CouponBean.ItemsBean>)

        } else {
            mAdapter.onLoad(main1bean.items as ArrayList<CouponBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var startindex = 0
    val pageSize = 10

    override fun getLayoutRes(): Int = R.layout.couponfragment


    lateinit var sectionCouponPresent: SectionCouponPresent
    lateinit var present: SectionOrderPresent

    var list = ArrayList<CouponBean.ItemsBean>()

    lateinit var mAdapter: CouponFragmentAdapter

    override fun initView() {


        mAdapter = CouponFragmentAdapter(list, context)
        sectionCouponPresent = SectionCouponPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()





        var textview = TextView(context)
        textview.text = "查看已失效的优惠券"
        textview.textSize = 25f
        textview.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary))
        mAdapter.addFooterView(textview,0)


    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)


        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()

        startindex++
        sectionCouponPresent.coupon(status, startindex, pageSize, "load")

    }


    override fun initData() {
        Log.i("TAG", "Main2_1Fragment")

    }


}