package com.ppwc.restaurant.views

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.recyclerView
import com.ppwc.restaurant.adapter.RecommendProductAdapter
import com.ppwc.restaurant.ipresent.ShopPresent
import com.ppwc.restaurant.iview.RecommendProductView
import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.recommendproductfragment.*

class RecommendProductFragment : BaseFragment(), RecommendProductView {
    override fun onRecommendProductValue(model: RecommendProductBean, flag: String) {
        if ("refresh" == flag) {


            if (pageIndex == 0 && "refresh" == flag && model.items != null && model.items.isEmpty()) {
                swipeLayout.finishRefresh()
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view
            } else {

                mAdapter?.setNewData(model.items)


            }


        } else {

            mAdapter?.addData(model.items)
            if (model.items != null && model.items.size > 0 && model.items.size < pageSize) {
                mAdapter?.loadMoreComplete()
            } else {
                mAdapter?.loadMoreEnd()
            }

        }

    }

    override fun onFault(errorMsg: String?) {
        if (pageIndex == 0) {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }
    }

    override fun getLayoutRes(): Int = R.layout.recommendproductfragment


    var pageIndex = 0
    val pageSize = 15

    val list = ArrayList<RecommendProductBean.ItemsBean>()
    var mAdapter: RecommendProductAdapter? = null

    var present: ShopPresent? = null


    override fun initView() {


        val id = arguments?.getString("id")

        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()



        present = ShopPresent(mActivity, this)
        present?.products("1009", pageIndex, pageSize, "refresh")



        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(ProductDetailsFragment.newInstance((adapter.data[position] as RecommendProductBean.ItemsBean).itemId))
        }


        mAdapter?.setOnItemChildClickListener { adapter, view, position ->

        }

    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mAdapter = RecommendProductAdapter(list)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
        }


    }

    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
    }

    private fun refresh() {

        pageIndex = 0

        present?.products("1009", pageIndex, pageSize, "refresh")
    }


    private fun loadMore() {

        pageIndex++
        present?.products("1009", pageIndex, pageSize, "load")
    }


    companion object {
        fun newInstance(id: String): RecommendProductFragment {
            val fragment = RecommendProductFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }


}