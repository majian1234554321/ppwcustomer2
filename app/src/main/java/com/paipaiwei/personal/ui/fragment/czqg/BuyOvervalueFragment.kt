package com.paipaiwei.personal.ui.fragment.czqg

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.QiangPaiListAdapter
import com.paipaiwei.personal.bean.QiangPaiListBean
import com.paipaiwei.personal.present.QiangPaiPresent
import com.paipaiwei.personal.ui.fragment.QiangPaiFragment
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.base.BaseView
import com.yjhh.common.present.BasePresent
import io.reactivex.Observable
import kotlinx.android.synthetic.main.buyovervaluefragment.*
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class BuyOvervalueFragment : BaseFragment(), BuyOvervalueService.BuyOvervalueView {
    override fun onBuyOvervalue(response: String?, flag: String?) {

        Log.i("BuyOvervalueFragment", response)

        when (flag) {
            "overBynav" -> {
                val model = Gson().fromJson<OverByNavBean>(response, OverByNavBean::class.java)

                model.navs.forEach {
                    mTabLayout.addTab(it.title)
                }



                mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabReselected(p0: TabLayout.Tab?) {

                    }

                    override fun onTabUnselected(p0: TabLayout.Tab?) {
                        // TODO("not implemented") //To change body of created functions use File ||
                    }

                    override fun onTabSelected(p0: TabLayout.Tab?) {
                        if (p0 != null) {
                            tradeCode = model.navs[p0.position].title
                            initRefreshLayout()
                        }
                    }

                })


            }
            "refresh" -> {

            }

            "loadMore" -> {

            }
            else -> {
            }
        }

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.buyovervaluefragment


    override fun initView() {
        present = BuyOvervaluePresent(mActivity, this)
        present?.overBynav("overBynav")

        initAdapter()
        initRefreshLayout()


    }

    var present: BuyOvervaluePresent? = null
    var pageIndex = 0
    val pageSize = 15
    var tradeCode: String? = "" //类型 0今日抢拍 1 明日抢拍
    val listValue = ArrayList<MultiItemEntity>()
    var mAdapter: QiangPaiListAdapter? = null


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
                        tradeCode
                    )
                )

            } else {

            }


        }

    }

    private fun refresh() {
        pageIndex = 0
        present?.overByList(tradeCode, pageIndex, pageSize, "refresh")

    }


    private fun loadMore() {
        pageIndex++
        present?.overByList(tradeCode, pageIndex, pageSize, "loadMore")

    }


    class BuyOvervaluePresent(var context: Context, var view: BuyOvervalueService.BuyOvervalueView) : BasePresent() {
        fun overByList(tradeCode: String?, pageIndex: Int, pageSize: Int, flag: String?) {
            map.clear()
            map["tradeCode"] = tradeCode
            map["pageIndex"] = pageIndex.toString()
            map["pageSize"] = pageSize.toString()
            toSubscribe2(ApiServices.getInstance().create(BuyOvervalueService::class.java).overByList(map),
                object : ProcessObserver2(context) {
                    override fun processValue(response: String?) {
                        view.onBuyOvervalue(response, flag)
                    }

                    override fun onFault(message: String) {
                        view.onFault(message)
                    }

                })
        }

        fun overBynav(flag: String?) {
            toSubscribe2(ApiServices.getInstance().create(BuyOvervalueService::class.java).overBynav(),
                object : ProcessObserver2(context) {
                    override fun processValue(response: String?) {
                        view.onBuyOvervalue(response, flag)
                    }

                    override fun onFault(message: String) {
                        view.onFault(message)
                    }

                })
        }

    }


    data class OverByNavBean(
        val navs: List<Nav>,
        val title: String
    )

    data class Nav(
        val code: String,
        val ifCurr: Boolean,
        val title: String,
        val value: String
    )


    class BuyOvervalueAdapter(data: List<MultiItemEntity>) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {
        override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {

            when (helper?.itemViewType) {
                TYPE_LEVEL_0 -> {

                }

                TYPE_LEVEL_1 -> {

                }
            }
        }


        init {
            addItemType(QiangPaiListAdapter.TYPE_LEVEL_0, R.layout.buyovervalueadapter_head)
            addItemType(QiangPaiListAdapter.TYPE_LEVEL_1, R.layout.buyovervalueadapter)
        }

        companion object {
            const val TYPE_LEVEL_0 = 0
            const val TYPE_LEVEL_1 = 1

        }


    }

}