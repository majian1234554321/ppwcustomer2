package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.qiangpaifragment.*


class QiangPaiFragment : BaseFragment(), View.OnClickListener, QiangPaiService.QiangPaiView {
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
            tv_Name.text = modle.shopName
//        tv_info =
//        tv_useTime =
//        tv_time =
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
                        start(OnePayMoneyFragment.newInstance(response,"限时抢拍"))
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
                present?.qiangPai(idValue, typeValue, "qiangPai")
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
        val costPrice: Double,
        val count: Int,
        val countText: String,
        val describe: String,
        val endTime: Int,
        val id: Int,
        val imageUrl: String,
        val markPrice: Double,
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