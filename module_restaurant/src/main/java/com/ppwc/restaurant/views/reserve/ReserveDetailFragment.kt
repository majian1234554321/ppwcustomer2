package com.ppwc.restaurant.views.reserve

import android.opengl.Visibility
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.reservedetailfragment.*

class ReserveDetailFragment : BaseFragment(), ReserveService.ReserveView {
    override fun reserveSuccess(jsonValue: String?, flag: String?) {
        Log.i("cancelReservation", jsonValue)
        mActivity.onBackPressed()
    }

    override fun onFault(errorMsg: String?) {
        Log.i("cancelReservation", errorMsg)
    }

    override fun getLayoutRes(): Int = R.layout.reservedetailfragment

    var id: String? = null
    override fun initView() {

        ImmersionBar.setTitleBar(mActivity, tbv_title)

        id = arguments?.getString("id")
        val map = ArrayMap<String, String>()
        map.put("id", id)

        ApiServices.getInstance().create(ReserveService::class.java)
            .detailReservation(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("detailReservation", response)
                    val model = Gson().fromJson<ReserveDetailBean>(response, ReserveDetailBean::class.java)

                    iev1.setTextContent(model.shopName)
                    iev2.setTextContent(model.timeText)
                    iev3.setTextContent(model.userCountText)
                    iev4.setTextContent(model.userMobile)
                    tv_right22.text = model.remark


                    when (model.status) { //	0申请中 1接受 2用户已取消 3店家取消 4超时取消（已过时） 5已完成
                        0 -> {
                            iv_image.setBackgroundResource(R.drawable.ddyydd)
                        }
                        1 -> {
                            iv_image.setBackgroundResource(R.drawable.yijieshouyuyue)
                        }
                        2 -> {
                            iv_image.setBackgroundResource(R.drawable.guoshiyydd)
                            tv_1.text= "已取消预约"
                            tv_2.visibility = View.GONE
                            tv_3.visibility = View.VISIBLE
                            tv_4.visibility = View.VISIBLE
                            tv_4.text = "取消原因: ${model.cause}"
                            tv_3.text = "用户主动取消"
                        }
                        3 -> {
                            iv_image.setBackgroundResource(R.drawable.guoshiyydd)
                            tv_1.text= "已取消预约"
                            tv_2.visibility = View.GONE
                            tv_3.visibility = View.VISIBLE
                            tv_3.text = "商家主动取消"
                            tv_4.visibility = View.VISIBLE
                            tv_4.text = "取消原因: ${model.cause}"

                        }

                        4 -> {
                            iv_image.setBackgroundResource(R.drawable.guoshiyydd)
                            tv_1.text = model.statusText
                            tv_2.visibility = View.GONE


                        }
                        5 -> {
                            iv_image.setBackgroundResource(R.drawable.yijieshouyuyue)
                        }
                        else -> {
                        }
                    }


                }

                override fun onFault(message: String) {
                    Log.i("detailReservation", message)
                }
            })

        tv_2.setOnClickListener {
             start(ReserveCancelFragment.newInstance(id))


        }




        val dis = RxBus.default.toFlowable(Bundle::class.java).subscribe {
            if (it != null) {
                val value = it?.getString("value")
                val id = it?.getString("id")



                val present = ReservePresent(mActivity, this)
                present?.cancelReservation(id, value,"cancelReservation")
            }
        }

        compositeDisposable.add(dis)








    }




    companion object {
        fun newInstance(id: String?): ReserveDetailFragment {
            val fragment = ReserveDetailFragment()
            val bundle = Bundle()


            bundle.putString("id", id)

            fragment.arguments = bundle
            return fragment
        }
    }

    data class ReserveDetailBean(
        val genderText: String,
        val id: Int,
        val remark: String,
        val shopId: Int,
        val shopName: String,
        val status: Int,
        val statusDisplayText: String,
        val statusText: String,
        val time: Int,
        val timeText: String,
        val timeTotal: Int,
        val userCount: Int,
        val userCountText: String,
        val userId: Int,
        val userMobile: String,
        val userName: String,
    val cause:String
    )


}