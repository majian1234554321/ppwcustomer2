package com.paipaiwei.personal.ui.activity.pai

import android.widget.Toast
import com.paipaiwei.personal.R
import com.ppwc.restaurant.adapter.RecommendProductAdapter
import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.pai1fragment.*

class Pai1Fragment : BaseFragment(), PaiService.PaiView {
    override fun PaiValue(model: String?, flag: String) {
        if ("refresh" == flag) {

        } else {

        }
    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.pai1fragment

    var present: PaiPresent? = null
    var pageIndex = 0
    val pageSize = 15
    val type = "0" //类型 0今日抢拍 1 明日抢拍


    val list = ArrayList<PaiBean>()
    var mAdapter: PaiAdapter? = null

    override fun initView() {
        PaiPresent(mActivity, this)

        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()

    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mAdapter = PaiAdapter(list)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
        }


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
        present?.qiangPai(type, pageIndex, pageSize, "refresh")
    }


    private fun loadMore() {
        pageIndex++
        present?.qiangPai(type, pageIndex, pageSize, "loadMore")
    }


}