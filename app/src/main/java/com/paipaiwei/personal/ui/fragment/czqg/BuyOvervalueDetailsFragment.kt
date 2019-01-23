package com.paipaiwei.personal.ui.fragment.czqg

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.apis.QiangPaiService
import com.paipaiwei.personal.bean.QiangPaiResultBean
import com.paipaiwei.personal.present.QiangPaiPresent
import com.paipaiwei.personal.ui.activity.onepay.OnePayMoneyFragment
import com.paipaiwei.personal.ui.fragment.QiangPaiDialogFragment
import com.paipaiwei.personal.ui.fragment.QiangPaiFragment

import com.yjhh.common.BaseApplication

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.RxCountDown
import com.yjhh.common.utils.TextStyleUtils

import kotlinx.android.synthetic.main.buyovervaluedetailsfragment.*


class BuyOvervalueDetailsFragment : BaseFragment(), QiangPaiService.QiangPaiView {

    override fun getLayoutRes(): Int = R.layout.buyovervaluedetailsfragment

    override fun qiangPaiValue(response: String?, flag: String?) {
        Log.i("QiangPaiFragment", response)
        if ("detail" == flag) {
            val modle = Gson().fromJson<QiangPaiFragment.QiangPaiDetailsBean>(
                response,
                QiangPaiFragment.QiangPaiDetailsBean::class.java
            )

            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                iv_image,
                modle?.imageUrl,
                R.drawable.icon_place3,
                R.drawable.icon_place3,
                0
            )

            when (modle.status) { //0即将开始 1进行中 2已结束/已拍完/已过期
                0 -> {
                    tv_submit.text = "即将开拍"
                    // tv_countdown.text = TimeUtil.stampToDate(modle.beginTime.toString(), "HH:mm")
                    tv_submit.setBackgroundResource(R.drawable.button_bukedianji2x)
                }
                1 -> {

                    val dis = RxCountDown.countdown(modle.time).subscribe {
                        if (it == 0) {
                            tv_submit.text = "已结束"
                            tv_submit.isEnabled = false
                            tv_submit.setTextColor(Color.parseColor("#B5B5B5"))
                            tv_submit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E6E6E6"))
                            // tv_countdown.visibility = View.INVISIBLE
                        } else {
//                            if (tv_countdown != null)
//                                tv_countdown.text = TimeUtil.secondToTime(it.toLong())
                        }
                    }
                    compositeDisposable.add(dis)


                }
                else -> {
                    tv_submit.text = "已拍完"
                    tv_submit.isEnabled = false
                    tv_submit.setTextColor(Color.parseColor("#B5B5B5"))
                    tv_submit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E6E6E6"))

                }
            }





            tv_Name.text = modle.shopName
            // tv_des.text = modle.describe

            val price1 = "￥${modle.markPrice}"
            tv_price1.text = TextStyleUtils.changeTextAa(price1, 0, 1, 10)
            tv_price2.text = "￥${modle.costPrice}"
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

    }

    var present: QiangPaiPresent? = null
    var idValue: String? = null
    var typeValue: String? = null
    var jumpTypeValue:String? = null


    override fun initView() {
        jumpTypeValue = arguments?.getString("jumpType")

        if ("STATUS" ==jumpTypeValue){
            ImmersionBar.setTitleBar(activity, tbv_title)
        }



        idValue = arguments?.getString("id")
        typeValue = arguments?.getString("type")
        present = QiangPaiPresent(mActivity, this)
        present?.detail(idValue, "detail")

    }


    companion object {
        fun newInstance(id: String?, type: String?,jumpType:String?): BuyOvervalueDetailsFragment {
            val fragment = BuyOvervalueDetailsFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            bundle.putString("type", type)
            bundle.putString("jumpType",jumpType)
            fragment.arguments = bundle
            return fragment
        }
    }

}
