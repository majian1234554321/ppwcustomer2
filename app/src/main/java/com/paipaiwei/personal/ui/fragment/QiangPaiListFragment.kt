package com.paipaiwei.personal.ui.fragment

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhon.appupdate.utils.ScreenUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.QiangPaiListAdapter
import com.paipaiwei.personal.apis.QiangPaiService
import com.paipaiwei.personal.bean.QiangPaiListBean
import com.paipaiwei.personal.interfaces.SectionDecoration

import com.paipaiwei.personal.present.QiangPaiPresent
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment

import kotlinx.android.synthetic.main.qiangpailistfragment.*

class QiangPaiListFragment : BaseFragment(), QiangPaiService.QiangPaiView, RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb1 -> {
                rb1.setTextColor(Color.WHITE)
                rb1.setBackgroundColor(Color.parseColor("#F9572D"))
                rb2.setTextColor(Color.parseColor("#333333"))
                rb2.setBackgroundColor(Color.WHITE)

                type = "0"
                pageIndex = 1
                swipeLayout.autoRefresh()
            }
            R.id.rb2 -> {
                rb2.setTextColor(Color.WHITE)
                rb2.setBackgroundColor(Color.parseColor("#F9572D"))
                rb1.setTextColor(Color.parseColor("#333333"))
                rb1.setBackgroundColor(Color.WHITE)

                type = "1"
                pageIndex = 1
                swipeLayout.autoRefresh()
            }
        }
    }

    var present: QiangPaiPresent? = null
    var pageIndex = 0
    val pageSize = 15
    var type: String? = "0" //类型 0今日抢拍 1 明日抢拍
    val listValue = ArrayList<MultiItemEntity>()
    var mAdapter: QiangPaiListAdapter? = null


    override fun qiangPaiValue(response: String?, flag: String?) {

        val model = Gson().fromJson<QiangPaiListBean>(response, QiangPaiListBean::class.java)

        val newList = ArrayList<MultiItemEntity>()
        if (model?.items != null) {
            model.items.forEachIndexed { index, itemsBeanX ->

                val headValue = HeadValue(itemsBeanX.name, itemsBeanX.statusText)

                newList.add(headValue)

                itemsBeanX.items.forEach {
                    newList.add(it)
                }
            }


            if ("refresh" == flag) {
                mAdapter?.setNewData(newList)
            } else {
                if (model.items.size != pageSize) {
                    mAdapter?.loadMoreEnd()
                }
            }

        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.qiangpailistfragment

    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)
        present = QiangPaiPresent(mActivity, this)
        initAdapter()
        initRefreshLayout()
        rg.setOnCheckedChangeListener(this)

    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()
    }

    private fun initAdapter() {

        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = QiangPaiListAdapter(listValue)
        mRecyclerView.adapter = mAdapter


        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter?.setOnItemClickListener { adapter, view, position ->


            if (adapter.data[position] is QiangPaiListBean.ItemsBeanX.ItemsBean) {
                start(
                    QiangPaiFragment.newInstance(
                        (adapter.data[position] as QiangPaiListBean.ItemsBeanX.ItemsBean).id,
                        type
                    )
                )

            } else {

            }


        }

    }

    private fun refresh() {
        pageIndex = 0
        present?.qiangPaiList(type, pageIndex, pageSize, "refresh")

    }


    private fun loadMore() {
        pageIndex++
        present?.qiangPaiList(type, pageIndex, pageSize, "load")

    }


    data class HeadValue(var leftValue: String, var rightValue: String) : MultiItemEntity {
        override fun getItemType(): Int {
            return QiangPaiListAdapter.TYPE_LEVEL_0
        }
    }


}