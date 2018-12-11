package com.yjhh.ppwcustomer.ui.activity.coupon

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.CouponAdapter
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.common.utils.Util.toast
import com.yjhh.ppwcustomer.present.SectionCouponPresent
import com.yjhh.ppwcustomer.view.CouponView
import kotlinx.android.synthetic.main.enablecouponfragment.*

class DisenableCouponFragment : BaseFragment(), CouponView {
    val status = "0"    // 状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    private val startindex = 0
    val pageSize = 100


    private var mAdapter: CouponAdapter? = null

    override fun onSuccess(main1bean: CouponBean, flag: String) {

        mAdapter = CouponAdapter(main1bean.items)
        recyclerView.adapter = mAdapter
    }

    override fun onFault(errorMsg: String?) {

    }

    lateinit var sectionCouponPresent: SectionCouponPresent

    override fun getLayoutRes(): Int = R.layout.enablecouponfragment

    override fun initView() {
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)


        sectionCouponPresent = SectionCouponPresent(context, this)

        initRefreshLayout()
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")



        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val intent = Intent()
            intent.putExtra("address", "AAAA")
            mActivity.setResult(BaseActivity.RESULT_OK, intent)
            mActivity.finish()

        }

    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")

    }


}
