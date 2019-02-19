package com.ppwc.restaurant.views.reserve

import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.reservelist1fragment.*


class ReserveList1Fragment : BaseFragment(), ReserveService.ReserveView {
    override fun reserveSuccess(jsonValue: String?, flag: String?) {
        Log.i("reserveSuccess", jsonValue)
    }

    override fun onFault(errorMsg: String?) {
        Log.i("reserveSuccess", errorMsg)
    }

    override fun getLayoutRes(): Int = R.layout.reservelist1fragment

    var present: ReservePresent? = null
    var status = "0"
    var pageIndex = 0
    var pageSize = 15
    override fun initView() {

        present = ReservePresent(mActivity, this)

        initAdapter()
        initRefreshLayout()


    }


    private fun initAdapter() {

//        mAdapter.setOnLoadMoreListener({
//            loadMore()
//        }, mRecyclerView)
//
//
//
//        mRecyclerView.adapter = mAdapter
//
//        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
//            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
//            }
//        })
    }


    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(mActivity))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            swipeLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()

    }


    private fun refresh() {

        pageIndex = 0
        present?.userReservation(status, pageIndex, pageSize, "refresh")

    }


    private fun loadMore() {


        pageIndex++
        present?.userReservation(status, pageIndex, pageSize, "loadMore")

    }


}