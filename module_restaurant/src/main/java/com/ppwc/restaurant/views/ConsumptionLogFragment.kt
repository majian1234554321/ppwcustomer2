package com.ppwc.restaurant.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.consumptionlogfragment.*

class ConsumptionLogFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.consumptionlogfragment

    var mAdapter: ConsumptionLogAdapter? = null
    val listValue = ArrayList<String>()
    var pageIndex = 0
    val pageSize = 15

    companion object {
        fun newInstance(id: String?): ConsumptionLogFragment {
            val fragment = ConsumptionLogFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment


        }
    }

    fun onEmptyView() {
        val view = View.inflate(mActivity, R.layout.emptyview, null)
        view.findViewById<ImageView>(R.id.iv_image).setBackgroundResource(R.drawable.xiaofeijil012x)
        view.findViewById<TextView>(R.id.tv_tips).text = "暂无消费记录"
        mAdapter?.emptyView = view
    }

    override fun initView() {

        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()

    }

    private fun initAdapter() {

        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ConsumptionLogAdapter(listValue)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)


    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }

    private fun refresh() {
        pageIndex = 0
        // sectionCouponPresent.usermessage(status, share, pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        // sectionCouponPresent.usermessage(status, share, pageIndex, pageSize, "load")

    }


    class ConsumptionLogAdapter(data: List<String>) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.consumptionlogadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }

    }


}