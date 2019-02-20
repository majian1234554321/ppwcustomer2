package com.ppwc.restaurant.views.reserve

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.RxBus
import kotlinx.android.synthetic.main.reservelist1fragment.*
import kotlinx.android.synthetic.main.reservelistfragment.*

class ReserveList2Fragment : BaseFragment(), ReserveService.ReserveView {
    override fun reserveSuccess(jsonValue: String?, flag: String?) {
        Log.i("reserveSuccess", jsonValue)


        if ("cancelReservation" == flag) {
            refresh()
        } else {
            val model = Gson().fromJson<ReserveListBean>(jsonValue, ReserveListBean::class.java)
            if (model.items != null) {
                if ("refresh" == flag) {
                    if (model.items != null && model.items.isNotEmpty()) {
                        mAdapter?.setNewData(model.items)
                    } else {
                        val view = View.inflate(mActivity, R.layout.emptyview, null)
                        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"

                        mAdapter?.data?.clear()
                        mAdapter?.notifyDataSetChanged()
                        mAdapter?.setEmptyView(R.layout.emptyview, mRecyclerView)
                    }

                } else {
                    mAdapter?.addData(model.items)

                    if (model != null && model.items.size == 15) {
                        mAdapter?.loadMoreComplete()
                    } else {
                        mAdapter?.loadMoreEnd()
                    }
                }
            }
        }


    }

    override fun onFault(errorMsg: String?) {
        Log.i("reserveSuccess", errorMsg)


    }

    override fun getLayoutRes(): Int = R.layout.reservelist1fragment

    var present: ReservePresent? = null
    var status = "1"
    var pageIndex = 0
    var pageSize = 15
    var listValue = ArrayList<ReserveListBean.ItemsBean>()

    var mAdapter: ReserveListAdapter? = null
    var id: String? = null

    override fun initView() {

        present = ReservePresent(mActivity, this)

        initAdapter()
        initRefreshLayout()


        val dis = RxBus.default.toFlowable(Bundle::class.java).subscribe {
            if (it != null) {
                val value = it?.getString("value")
                val id = it?.getString("id")
                if (value != null) {
                    Toast.makeText(_mActivity, value, Toast.LENGTH_SHORT).show()
                }



                present?.cancelReservation(id, value,"cancelReservation")
            }
        }

        compositeDisposable.add(dis)


    }


    private fun initAdapter() {

        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ReserveListAdapter(listValue)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            //  start(ReserveCancelFragment())
            (parentFragment as ReserveListFragment).startBrotherFragment(
                ReserveCancelFragment.newInstance(
                    mAdapter?.data?.get(
                        position
                    )?.id
                )
            )
        }
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            //  start(ReserveDetailFragment())

            (parentFragment as ReserveListFragment).startBrotherFragment(
                ReserveDetailFragment.newInstance(
                    mAdapter?.data?.get(
                        position
                    )?.id
                )
            )
        }

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