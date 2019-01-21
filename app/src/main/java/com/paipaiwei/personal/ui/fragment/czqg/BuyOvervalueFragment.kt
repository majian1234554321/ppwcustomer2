package com.paipaiwei.personal.ui.fragment.czqg

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
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

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.buyovervaluefragment


    override fun initView() {
        present = BuyOvervaluePresent(mActivity, this)


        initAdapter()
        initRefreshLayout()


    }

    var present: BuyOvervaluePresent? = null
    var pageIndex = 0
    val pageSize = 15
    var type: String? = "0" //类型 0今日抢拍 1 明日抢拍
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
                        type
                    )
                )

            } else {

            }


        }

    }

    private fun refresh() {
        pageIndex = 0
        //present?.overByList(type, pageIndex, pageSize, "refresh")

    }


    private fun loadMore() {
        pageIndex++
        // present?.overByList(type, pageIndex, pageSize, "load")

    }


    class BuyOvervaluePresent(var context: Context, var view: BuyOvervalueService.BuyOvervalueView) : BasePresent() {
        fun overByList(id: String?, flag: String?) {
            map.clear()
            map.put("id", id)
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
    }


}