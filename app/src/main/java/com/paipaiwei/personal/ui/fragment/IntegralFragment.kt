package com.paipaiwei.personal.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.IntegralAdapter
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.IntegralBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.view.IntegralView
import kotlinx.android.synthetic.main.integralfragment.*
import android.text.SpannableString
import android.text.Spanned
import android.view.ViewGroup
import android.text.style.RelativeSizeSpan


class IntegralFragment : BaseFragment(), IntegralView {


    override fun onFault(errorMsg: String?) {
        //
    }

    override fun onSuccess(value: IntegralBean, flag: String) {
        //

        if ("refresh" == flag) {

            var textintegral = "0 个"

            if (value.integral != null) {
                textintegral = value.integral.plus(" 个")
            }
            val spannableString = SpannableString(textintegral)
            val sizeSpan01 = RelativeSizeSpan(0.3f)
            spannableString.setSpan(
                sizeSpan01,
                textintegral.length - 1,
                textintegral.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            );
            tv_count?.text = spannableString
            mAdapter?.setNewData(value.items)

            mAdapter?.disableLoadMoreIfNotFullPage()


        } else {

            if (value.items.size < pageSize) {
                mAdapter?.loadMoreEnd()
            } else {
                mAdapter?.loadMoreComplete()
            }
            mAdapter?.addData(value.items)
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
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

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

        val headView: View =
            layoutInflater.inflate(R.layout.integralheadlayout, mRecyclerView.parent as ViewGroup, false)
        tv_count = headView.findViewById<TextView>(R.id.tv_count)

        mAdapter?.addHeaderView(headView)

    }

    private fun refresh() {
        startindex = 0
        present?.useraccountjoin(type, startindex, pageSize, "refresh")

    }


    private fun loadMore() {

        startindex++
        present?.useraccount(type, startindex, pageSize, "load")

    }


}
