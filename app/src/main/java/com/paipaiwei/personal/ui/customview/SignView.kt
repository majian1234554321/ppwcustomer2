package com.paipaiwei.personal.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable

import androidx.core.content.ContextCompat

import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.SignBean
import kotlinx.android.synthetic.main.signview.view.*


class SignView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr
) {


    fun setType(type: SignBean.ItemsBean) {
        tv_bottom.text = type.dateText
        iv_head.visibility = if (type.ifJinLi) View.VISIBLE else View.INVISIBLE

        when (type.type) {
            0 -> {  //0未签 1已签 2漏签

                if (type.ifPai) {
                    iv_mid.setBackgroundResource(R.drawable.unsignpai) //未签 有拍
                } else {
                    iv_mid.setBackgroundResource(R.drawable.colore6_dot) //未签 无拍
                }

            }

            1 -> {

                if (type.ifPai) {
                    iv_mid.setBackgroundResource(R.drawable.signtodaypai)  // 已签 有拍
                } else {
                    iv_mid.setBackgroundResource(R.drawable.signtodaynopai) //已签 无拍
                }


            }

            2 -> {
                if (type.ifPai) {
                    iv_mid.setBackgroundResource(R.drawable.beforeunsignpai) //漏签 有拍
                } else {
                    iv_mid.setBackgroundResource(R.drawable.color_dot)  //漏签 无拍
                }
            }
            else -> {
            }

        }
    }


    init {


        val view = View.inflate(context, R.layout.signview, this)


    }


}
