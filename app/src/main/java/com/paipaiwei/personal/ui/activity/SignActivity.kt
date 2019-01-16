package com.paipaiwei.personal.ui.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.amap.api.mapcore2d.fv
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.SignBean
import com.paipaiwei.personal.bean.SignResultBean

import com.paipaiwei.personal.present.SignPresent
import com.paipaiwei.personal.ui.fragment.SignFragment
import com.paipaiwei.personal.ui.fragment.SignFragment2
import com.paipaiwei.personal.view.SignView

import com.yjhh.common.base.BaseActivity
import com.zzhoujay.richtext.RichText

import kotlinx.android.synthetic.main.activity_singn.*

import java.util.*


class SignActivity : BaseActivity(), View.OnClickListener, SignView {
    override fun onSuccessList(response: SignBean?) {

        response?.items?.forEachIndexed { index, itemsBean ->
            sv?.get(index)?.setType(itemsBean)
        }


        if (response?.ifSign!!) {
            mb_sign.text = "已签到"
            mb_sign.setBackgroundResource(R.drawable.qiandao_yiqianyiqian)
        } else {
            mb_sign.text = "签到"
            mb_sign.setBackgroundResource(R.drawable.qiandao_weiqia)
        }

        tv_tips.text = "已连续签到${response.days}天"


        var textintegral = "${response.daysSum}天"

        val spannableString = SpannableString(textintegral)
        val sizeSpan01 = RelativeSizeSpan(0.6f)
        spannableString.setSpan(
            sizeSpan01,
            textintegral.length - 1,
            textintegral.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        );
        sign_count?.text = spannableString



        val layout =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)



        val w = View.MeasureSpec.makeMeasureSpec(0,
            View.MeasureSpec.UNSPECIFIED);
        val h = View.MeasureSpec.makeMeasureSpec(0,
            View.MeasureSpec.UNSPECIFIED);
        rl1.measure(w, h);




        RichText.from(response.rule).into(text);



    }

    override fun onSuccessSign(response: SignResultBean?) {


        mb_sign.isEnabled = false

        if (response?.ifGetJinLi!!) {
            SignFragment(response.title).show(supportFragmentManager, "TAG")
        } else {
            SignFragment2(response.title).show(supportFragmentManager, "TAG2")

        }

        present?.userSignInList()


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_sign -> {
               // present?.sign()


                SignFragment("AAAAA").show(supportFragmentManager, "TAG2")

            }

            R.id.sv_4 -> {

            }
            R.id.iv_back -> {
                this.finish()
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


        arrayOf(mb_sign, sv_4, iv_back).forEach {
            it.setOnClickListener(this)
        }







//        val tx = supportFragmentManager.beginTransaction()
//        tx.replace(R.id.frameLayout, frafment)
//        tx.commit()


    }


    override fun onDestroy() {
        super.onDestroy()
        RichText.clear(this@SignActivity);
    }


}
