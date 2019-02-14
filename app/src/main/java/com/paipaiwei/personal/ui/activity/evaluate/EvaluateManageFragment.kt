package com.paipaiwei.personal.ui.activity.evaluate

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.adapter.EvaluateManageAdapter
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.*
import com.paipaiwei.personal.present.EvaluatePresent
import com.paipaiwei.personal.view.EvaluateView


import kotlinx.android.synthetic.main.evaluatemanagefragment.*

import java.util.*

class EvaluateManageFragment : BaseFragment(), EvaluateView {

    override fun onFault(errorMsg: String?) {
        if (swipeLayout != null) {
            swipeLayout.finishRefresh()
        }

        if (startIndex == 0) {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }

    }

    override fun onSuccess(value: String?, flag: String) {


        val gson = Gson()
        val bean = gson.fromJson<EvaluateManageBean>(value, EvaluateManageBean::class.java)

        if (bean.items != null && bean.items.size > 0) {
            list.clear()
            for (i in 0 until bean.items.size) {
                val lv0 = bean.items[i]



                if (lv0.items != null && lv0.items.size > 0) {
                    lv0.items.forEachIndexed { index, subCommentsBean ->
                        if (index == lv0.items.size - 1) {
                            subCommentsBean.last = true
                            lv0.addSubItem(subCommentsBean)
                        } else {
                            subCommentsBean.last = false
                            lv0.addSubItem(subCommentsBean)
                        }
                    }
                } else {
                    val model = SubCommentsBean(
                        "", "", "", bean.items[i].id.toString(), 0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                        , true
                    )
                    lv0.addSubItem(model)
                }
                list.add(lv0)
            }



            when (flag) {
                "refresh" -> {
                    listAll.clear()
                    listAll.addAll(list)
                    if (swipeLayout != null) {
                        swipeLayout.finishRefresh()
                    }

                    if (startIndex == 0 && bean.items != null && bean.items.size == 0) {
                        val view = View.inflate(mActivity, R.layout.emptyview, null)
                        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                        mAdapter?.emptyView = view
                    } else {
                        mAdapter?.setNewData(list)
                    }

                }


                "load" -> {
                    if (bean.items != null && bean.items.size == pageSize) {
                        listAll.addAll(list)
                        mAdapter?.addData(listAll)
                        mAdapter?.loadMoreComplete()
                    } else {
                        listAll.addAll(list)
                        mAdapter?.addData(list)

                        mAdapter?.loadMoreEnd()
                    }


                }

            }

        } else {
            if (startIndex == 0 && swipeLayout != null) {
                swipeLayout.finishRefresh()

                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view

            }

            if ("load" == flag) {
                mAdapter?.loadMoreEnd()
            }
        }


        mAdapter?.expandAll()


    }


    override fun getLayoutRes(): Int = R.layout.evaluatemanagefragment

    var type = "0"//类别，默认null（null/0全部 1好评 2中评 3差评）
    var startIndex = 0
    val pageSize = 15
    var isHasfile = "0"

    var mAdapter: EvaluateManageAdapter? = null

    val list = ArrayList<MultiItemEntity>()
    val listAll = ArrayList<MultiItemEntity>()

    var present: EvaluatePresent? = null

    override fun initView() {



        present = EvaluatePresent(mActivity, this)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))

        mAdapter = EvaluateManageAdapter(mActivity, listAll)

//        for (i in mTitles.indices) {
//            mTabEntities.add(TabEntity(mTitles[i], R.mipmap.ic_launcher, R.mipmap.ic_launcher))
//        }

        initRefreshLayout()





        initAdapter()

        swipeLayout.autoRefresh()





        mAdapter?.setOnItemClickListener { adapter, view, position ->

            if (adapter.data[position] is EvaluateManageItemBean) {
                start(EvaluateDetailsFragment.newInstance((adapter.data[position] as EvaluateManageItemBean).id.toString()))
            } else {
                start(EvaluateDetailsFragment.newInstance((adapter.data[position] as SubCommentsBean).id))
            }


        }

        mAdapter?.expandAll()

    }

    private fun initAdapter() {


        recyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, recyclerView)

        mAdapter?.disableLoadMoreIfNotFullPage(recyclerView)

    }

    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }


    }

    private fun refresh() {
        startIndex = 0

        present?.allcomments( startIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        //
        startIndex++

        present?.allcomments( startIndex, pageSize, "load")


    }

}