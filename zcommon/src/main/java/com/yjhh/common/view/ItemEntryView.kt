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

import kotlinx.android.synthetic.main.itementryview.view.*


class ItemEntryView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr
) {

    fun setTextContent(name: String) {
        tv_content.text = name
    }

    fun getTextContent(): String {
        return tv_content.text.toString()
    }


    init {


        val type = context.theme.obtainStyledAttributes(attributeSet, R.styleable.ItemEntryView, defStyleAttr, 0)
        val textValue = type.getString(R.styleable.ItemEntryView_ievtextValue)
        val textColor =
            type.getColor(R.styleable.ItemEntryView_ievtextColor, ContextCompat.getColor(context, R.color.colorPrimary))


        val textContentValue =
            type.getColor(
                R.styleable.ItemEntryView_ievtextContentValue,
                ContextCompat.getColor(context, R.color.colorPrimary)
            )


        val textSize = type.getDimension(R.styleable.ItemEntryView_ievtextSize, 18f)

        val titleBarBackground = type.getColor(
            R.styleable.ItemEntryView_ievbackground,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )


        val ievdisplaytextContent = type.getBoolean(
            R.styleable.ItemEntryView_ievdisplaytextContent,
            false
        )


        val ievunderline = type.getBoolean(
            R.styleable.ItemEntryView_ievunderline,
            false
        )


        val ievarrow = type.getBoolean(
            R.styleable.ItemEntryView_ievarrow,
            true
        )



        type.recycle()

        val view = View.inflate(context, R.layout.itementryview, this)
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        tv_name.text = textValue
        tv_name.setTextColor(textColor)
        tv_name.textSize = textSize

        val line = view.findViewById<View>(R.id.line)

        if (ievunderline) {
            line.visibility = View.VISIBLE
        } else {
            line.visibility = View.GONE
        }

        if (ievarrow) {
            iv_arrow.visibility = View.VISIBLE
        } else {
            iv_arrow.visibility = View.GONE
        }

        val rl_background = view.findViewById<RelativeLayout>(R.id.rl_background)


        rl_background.setBackgroundColor(titleBarBackground)


    }


}
