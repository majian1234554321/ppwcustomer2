package com.paipaiwei.personal.ui.activity


import android.os.Bundle
import android.view.View
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.SignBean
import com.paipaiwei.personal.bean.SignResultBean
import com.paipaiwei.personal.present.SignPresent
import com.paipaiwei.personal.ui.fragment.SignFragment
import com.paipaiwei.personal.view.SignView

import com.yjhh.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_singn.*

class SignActivity : BaseActivity(), View.OnClickListener, SignView {
    override fun onSuccessList(response: SignBean?) {

        response?.items?.forEachIndexed { index, itemsBean ->
            sv?.get(index)?.setType(itemsBean)
        }


    }

    override fun onSuccessSign(response: SignResultBean?) {


        mb_sign.isEnabled = false

        if (response?.ifGetJinLi!!) {
            SignFragment("A").show(supportFragmentManager, "TAG")
        }

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_sign -> {

                present?.sign()


            }

            R.id.sv_4 -> {

            }

            else -> {
            }
        }
    }


    var present: SignPresent? = null
    var sv: Array<com.paipaiwei.personal.ui.customview.SignView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singn)


        present = SignPresent(this@SignActivity, this)

        present?.userSignInList()


        sv = arrayOf(sv_1, sv_2, sv_3, sv_4, sv_5, sv_6, sv_7)















        arrayOf(mb_sign, sv_4).forEach {
            it.setOnClickListener(this)
        }


    }
}
