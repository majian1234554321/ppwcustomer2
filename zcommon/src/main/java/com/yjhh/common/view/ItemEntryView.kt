package com.yjhh.common.view
import android.content.Context


import androidx.core.content.ContextCompat

import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.R
import com.yjhh.common.R.id.*


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

    fun setTextContent(name: String): ItemEntryView {
        tv_content.text = name
        return this
    }

    fun getTextContent(): String {
        return tv_content.text.toString()
    }

    fun setArrow() {
        iv_arrow.visibility = View.INVISIBLE
    }


    public  fun setLeftTitle(name: String?): ItemEntryView {
        tv_name.text = name
        return this
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




        val titleBarBackground = type.getColor(
            R.styleable.ItemEntryView_ievbackground,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )


        val paddingValue = type.getDimension(R.styleable.ItemEntryView_ievtextpadding, 0f)

        val ievdisplaytextContent = type.getBoolean(
            R.styleable.ItemEntryView_ievdisplaytextContent,
            false
        )


        val ievdrwableleft = type.getDrawable(
            R.styleable.ItemEntryView_ievdrwableleft

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

        tv_name.setPadding(paddingValue.toInt(), 0, 0, 0)




        tv_name.setCompoundDrawablesWithIntrinsicBounds(
            ievdrwableleft,
            null, null, null
        )
        tv_name.compoundDrawablePadding = 20



//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        textView2.setCompoundDrawables(drawable,null,null,null);




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
