package com.paipaiwei.personal.ui.fragment

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.apis.QiangPaiService
import com.paipaiwei.personal.bean.QiangPaiResultBean
import com.paipaiwei.personal.present.QiangPaiPresent
import com.paipaiwei.personal.ui.activity.onepay.OnePayMoneyFragment
import com.yjhh.common.BaseApplication

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.RxCountDown
import com.yjhh.common.utils.TextStyleUtils
import com.yjhh.common.utils.TimeUtil
import kotlinx.android.synthetic.main.qiangpaifragment.*
import kotlin.text.Typography.times


class QiangPaiFragment : BaseFragment(), View.OnClickListener, QiangPaiService.QiangPaiView {
    var statusValue = -1
    override fun qiangPaiValue(response: String?, flag: String?) {
        Log.i("QiangPaiFragment", response)



        if ("detail" == flag) {
            val modle = Gson().fromJson<QiangPaiDetailsBean>(response, QiangPaiDetailsBean::class.java)

            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                iv_image,
                modle?.imageUrl,
                R.drawable.icon_place_square,
                R.drawable.icon_place_square,
                0
            )
            statusValue = modle.status
            when (modle.status) { //0即将开始 1进行中 2已结束/已拍完/已过期
                0 -> {
                    tv_submit.text = "即将\n开拍"
                    tv_countdown.text = TimeUtil.stampToDate(modle.beginTime.toString(), "HH:mm")
                    tv_submit.setBackgroundResource(R.drawable.button_bukedianji2x)
                }
                1 -> {

                    val dis = RxCountDown.countdown(modle.time).subscribe {
                        if (it == 0&&tv_submit!=null) {
                            tv_submit.text = "已结束"
                            tv_submit.isEnabled = false
                            tv_submit.setBackgroundResource(R.drawable.button_bukedianji2x)
                            tv_countdown.visibility = View.INVISIBLE
                        } else {
                            if (tv_countdown != null)
                                tv_countdown.text = TimeUtil.secondToTime(it.toLong())
                        }
                    }
                    compositeDisposable.add(dis)


                }
                else -> {
                    tv_submit.text = "已拍完"
                    tv_submit.setBackgroundResource(R.drawable.button_bukedianji2x)

                }
            }





            tv_Name.text = modle.shopName


            val price1 = "￥${modle.costPrice}"
            tv_price1.text = TextStyleUtils.changeTextAa(price1, 0, 1, 10)
            tv_price2.text = "￥${modle.markPrice}"
            tv_price2.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            tv_info.text = modle.title
            tv_describe.text = modle.describe
        } else {
            val modle2 = Gson().fromJson<QiangPaiResultBean>(response, QiangPaiResultBean::class.java)
            var dialog: QiangPaiDialogFragment? = null
            if (modle2.status != 0) { //抢拍失败
                dialog = QiangPaiDialogFragment("抢拍失败", modle2.content, modle2.remark)
            } else {
                dialog = QiangPaiDialogFragment("抢拍成功", modle2.content, modle2.remark)
            }

            dialog.show(childFragmentManager, "TAG")

            dialog.setOnDialogClick(object : QiangPaiDialogFragment.OnDialogClickListener {
                override fun onDialogClick() {
                    if (modle2.status != 0) {
                        mActivity.onBackPressed()
                    } else {
                        start(OnePayMoneyFragment.newInstance(response, "限时抢拍"))
                    }

                    dialog.dismiss()
                }

            })
        }


    }

    override fun onFault(errorMsg: String?) {
        Log.i("QiangPaiFragment", errorMsg)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_rule -> {

            }
            R.id.tv_submit -> {
                when (statusValue) {
                    0 -> { ////0即将开始 1进行中 2已结束/已拍完/已过期
                        Toast.makeText(context, "活动暂未开始", Toast.LENGTH_SHORT).show()
                    }
                    1 ->{
                        present?.qiangPai(idValue, typeValue, "qiangPai")
                    }
                    2->{
                        Toast.makeText(context, "活动已结束", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                    }
                }

            }
            else -> {

            }
        }
    }

    var present: QiangPaiPresent? = null
    var idValue: String? = null
    var typeValue: String? = null

    override fun getLayoutRes(): Int = R.layout.qiangpaifragment

    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)
        arrayOf(tv_rule, tv_submit).forEach {
            it.setOnClickListener(this)
        }
        idValue = arguments?.getString("id")
        typeValue = arguments?.getString("type")
        present = QiangPaiPresent(mActivity, this)
        present?.detail(idValue, "detail")

    }

    companion object {
        fun newInstance(id: String?, type: String?): QiangPaiFragment {
            val fragment = QiangPaiFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            bundle.putString("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }


    data class QiangPaiDetailsBean(
        val beginTime: Int,
        val costPrice: String,
        val count: Int,
        val countText: String,
        val describe: String,
        val endTime: Int,
        val id: Int,
        val imageUrl: String,
        val markPrice: String,
        val memo: String,
        val price: Double,
        val rec: Boolean,
        val shopName: String,
        val status: Int,
        val statusText: String,
        val time: Int,
        val title: String,
        val total: Int,
        val type: Int
    )


}