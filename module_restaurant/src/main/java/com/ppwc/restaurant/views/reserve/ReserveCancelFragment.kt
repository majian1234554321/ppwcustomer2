package com.ppwc.restaurant.views.reserve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.OrderEvaluationAdapter
import com.sina.weibo.sdk.constant.WBConstants
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.RxBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.reservecancelfragment.*


class ReserveCancelFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reservecancelfragment

    override fun initView() {


        val id = arguments?.getString("id")


        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)

        ApiServices.getInstance().create(ReserveService::class.java).cancelCause()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("detailReservation", response)

                }

                override fun onFault(message: String) {
                    Log.i("detailReservation", message)
                }

            })



        rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb1 -> {
                    rb1.isChecked = true
                    et_remark.visibility = View.GONE
                }
                R.id.rb2 -> {
                    rb2.isChecked = true
                    et_remark.visibility = View.GONE
                }
                R.id.rb3 -> {
                    rb3.isChecked = true
                    et_remark.visibility = View.VISIBLE
                }

                else -> {
                }
            }
        }


        mb.setOnClickListener {

            val value = when {
                rb1.isChecked -> rb1.text.toString().trim()
                rb2.isChecked -> rb2.text.toString().trim()
                rb3.isChecked -> et_remark.text.toString().trim()
                else -> {
                    ""
                }
            }



            val bundle = Bundle()
            bundle.putString("id", id)
            bundle.putString("value", value)

            RxBus.default.post(bundle)





            mActivity.onBackPressed()


        }


    }


    companion object {
        fun newInstance(id: String?): ReserveCancelFragment {
            val fragment = ReserveCancelFragment()
            val bundle = Bundle()


            bundle.putString("id", id)

            fragment.arguments = bundle
            return fragment
        }
    }


    class ReserveCancelAdapter(data: List<String>) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.reservecanceladapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }

    }


}