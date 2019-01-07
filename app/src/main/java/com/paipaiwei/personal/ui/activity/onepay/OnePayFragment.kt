package com.paipaiwei.personal.ui.activity.onepay

import com.google.gson.Gson
import com.paipaiwei.personal.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.onepayfragment.*

class OnePayFragment : BaseFragment(), OnePayService.OnePayView {

    var id = ""
    var number = "1"

    override fun PaiValue(model: String?, flag: String) {
        val gson = Gson()

        if ("userProp" == flag) {
            val bean = gson.fromJson<OnePayBean>(model, OnePayBean::class.java)
            id = bean.propA.id
        }

        if ("oneMoneyBuy" == flag) {
            start(OnePayMoneyFragment.newInstance(model))
        }


    }

    override fun onFault(errorMsg: String?) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates

    }


    override fun getLayoutRes(): Int = R.layout.onepayfragment

    var present: OnePayPresent? = null
    override fun initView() {
        present = OnePayPresent(mActivity, this)
        present?.userProp("userProp")



        mb_buy.setOnClickListener {
            present?.oneMoneyBuy(id, number, "oneMoneyBuy")
        }


    }

}