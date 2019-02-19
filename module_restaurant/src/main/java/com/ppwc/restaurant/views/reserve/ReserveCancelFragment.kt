package com.ppwc.restaurant.views.reserve

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.OrderEvaluationAdapter
import com.sina.weibo.sdk.constant.WBConstants
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.reservecancelfragment.*
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class ReserveCancelFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reservecancelfragment

    override fun initView() {


        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)

        ApiServices.getInstance().create(ReserveService::class.java).cancelCause()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {


                }

                override fun onFault(message: String) {

                }

            })


    }


    class ReserveCancelAdapter(data: List<String>) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.reservecanceladapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }

    }


}