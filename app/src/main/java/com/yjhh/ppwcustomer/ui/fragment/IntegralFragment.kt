package com.yjhh.ppwcustomer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.IntegralAdapter
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.IntegralBean
import com.yjhh.ppwcustomer.present.SectionUselessPresent
import com.yjhh.ppwcustomer.view.IntegralView
import kotlinx.android.synthetic.main.integralfragment.*
import android.text.SpannableString
import android.text.Spanned
import android.view.ViewGroup
import android.text.style.RelativeSizeSpan




class IntegralFragment : BaseFragment(), IntegralView {
    override fun onFault(errorMsg: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(value: IntegralBean, flag: String) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if ("refresh" == flag) {


            var textintegral= "0个"

            if(value.integral!=null){
                textintegral = value.integral.plus("个")
            }


            val spannableString = SpannableString(textintegral)

            val sizeSpan01 = RelativeSizeSpan(0.5f)

            spannableString.setSpan(sizeSpan01, textintegral.length-1, textintegral.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tv_count?.text = spannableString





            mAdapter?.setNewData(value.items)

            mAdapter?.disableLoadMoreIfNotFullPage()
            //mAdapter.notifyDataSetChanged()

        } else {
            //  mAdapter.onLoad(main1bean.items as ArrayList<RecentlyBrowseBean.ItemsBean>)
            if (value.items.size < pageSize) {
                mAdapter?.loadMoreEnd()
            } else {
                mAdapter?.loadMoreEnd()
            }

        }

    }

    var startindex = 0
    val pageSize = 10
    var type = ""  // 类别，默认null（null/-1 全部 0余额 1积分）

    override fun getLayoutRes(): Int = R.layout.integralfragment

    var mAdapter: IntegralAdapter? = null
    var present: SectionUselessPresent? = null
    override fun initView() {
        present = SectionUselessPresent(mActivity, this)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = IntegralAdapter()

        addHeader()


        mRecyclerView.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()
    }


    var tv_count: TextView? = null

    private fun addHeader() {


        val headView: View = layoutInflater.inflate(R.layout.integralheadlayout, mRecyclerView.parent as ViewGroup, false)


        tv_count = headView.findViewById<TextView>(R.id.tv_count)



        mAdapter?.addHeaderView(headView)

    }


    private fun refresh() {

        startindex = 0
        present?.useraccountjoin(type, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        present?.useraccount(type, startindex, pageSize, "load")

    }


}
