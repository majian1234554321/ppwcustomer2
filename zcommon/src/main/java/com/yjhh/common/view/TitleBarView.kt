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
import com.yjhh.common.listener.NavigationOnClickListener
import kotlinx.android.synthetic.main.titlebarview.view.*


class TitleBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr
) {

    private lateinit var navigationOnClickListener: NavigationOnClickListener

    fun setOnNavigation(navigationOnClickListener: NavigationOnClickListener) {
        this.navigationOnClickListener = navigationOnClickListener
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

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val rl_background = view.findViewById<RelativeLayout>(R.id.rl_background)

        rl_background.setBackgroundColor(titleBarBackground)

        

        toolbar.setNavigationOnClickListener {
            navigationOnClickListener.OnNavigation()
        }




    }


}
