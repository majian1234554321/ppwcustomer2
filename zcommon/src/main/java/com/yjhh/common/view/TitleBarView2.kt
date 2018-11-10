package com.yjhh.common.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.yjhh.common.R
import com.yjhh.common.listener.LeftOnClickListener
import com.yjhh.common.listener.RightOnClickListener

import kotlinx.android.synthetic.main.titlebarview.view.*


class TitleBarView2 @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr
) {

    private lateinit var navigationOnClickListener: LeftOnClickListener
    private lateinit var rightOnClickListener: RightOnClickListener
    fun setLeftOnClick(navigationOnClickListener: LeftOnClickListener) {
        this.navigationOnClickListener = navigationOnClickListener
    }

    fun setRightOnClick(rightOnClickListener: RightOnClickListener) {
        this.rightOnClickListener = rightOnClickListener
    }

    fun setTitle(name: String) {
        tv_title.text = name
    }


    init {


        val type = context.theme.obtainStyledAttributes(attributeSet, R.styleable.TitleBarView, defStyleAttr, 0)
        val textValue = type.getString(R.styleable.TitleBarView_textValue)
        val textColor =
            type.getColor(R.styleable.TitleBarView_textColor, ContextCompat.getColor(context, R.color.colorPrimary))
        val textSize = type.getDimension(R.styleable.TitleBarView_textSize, 18f)

        val titleBarBackground = type.getColor(
            R.styleable.TitleBarView_titleBarBackground,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )

        type.recycle()

        val view = View.inflate(context, R.layout.titlebarview, this)
        val tv_title = view.findViewById<TextView>(R.id.tv_title)



        tv_title.text = textValue
        tv_title.setTextColor(textColor)
        tv_title.textSize = textSize


        val rl_background = view.findViewById<RelativeLayout>(R.id.rl_background)

        rl_background.setBackgroundColor(titleBarBackground)


        iv_right.setOnClickListener {
            rightOnClickListener.OnRightClick()
        }


        iv_back.setOnClickListener {
            navigationOnClickListener.OnLeftClick()
        }


    }


}
