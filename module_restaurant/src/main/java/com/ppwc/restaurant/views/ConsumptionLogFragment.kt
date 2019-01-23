package com.ppwc.restaurant.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.ppwc.restaurant.R
import com.ppwc.restaurant.ipresent.RestaurantOrderPresent
import com.ppwc.restaurant.iview.RestaurantOrderSerVice
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.TimeUtil
import kotlinx.android.synthetic.main.consumptionlogfragment.*

class ConsumptionLogFragment : BaseFragment(), RestaurantOrderSerVice.RestaurantOrderView {
    override fun onRestaurantOrder(model: String?, flag: String) {
        val bean = Gson().fromJson<CounsLogBean>(model, CounsLogBean::class.java)
        if ("refresh" == flag) {
            if (bean.items.isEmpty() && pageIndex == 0) {
                onEmptyView()
            } else {
                mAdapter?.setNewData(bean.items)
            }
        } else {
            if (bean.items.isNotEmpty() && bean.items.size == pageSize) {
                mAdapter?.addData(bean.items)
                mAdapter?.loadMoreComplete()
            } else {
                mAdapter?.addData(bean.items)
                mAdapter?.loadMoreEnd()
            }
        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.consumptionlogfragment

    var mAdapter: ConsumptionLogAdapter? = null
    val listValue = ArrayList<Item>()
    var pageIndex = 0
    val pageSize = 15

    var present: RestaurantOrderPresent? = null
    var type = "0"  // 类别，默认null（null/-1 全部 0余额 1积分）

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
        present = RestaurantOrderPresent(mActivity, this)
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
        present?.logs(type, pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        present?.logs(type, pageIndex, pageSize, "loadMore")

    }


    class ConsumptionLogAdapter(data: List<Item>) :
        BaseQuickAdapter<Item, BaseViewHolder>(R.layout.consumptionlogadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: Item?) {
            helper?.setText(R.id.tv_payType, item?.typeName)
                ?.setText(R.id.tv_payInfo, item?.content)
                ?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.createdTime))
                ?.setText(R.id.tv_price, mContext.getString(R.string.rmb_price_doubled, item?.money))
        }

    }


    data class CounsLogBean(
        val items: List<Item>,
        val pageCount: Int,
        val queryModel: QueryModel,
        val recordCount: Int
    )

    data class Item(
        val balance: Int,
        val content: String,
        val createdTime: String,
        val flag: Int,
        val freeze: Double,
        val money: Double,
        val total: Int,
        val tradeType: Int,
        val tradeTypeName: String,
        val type: Int,
        val typeName: String
    )

    data class QueryModel(
        val pageIndex: Int,
        val pageSize: Int
    )

}