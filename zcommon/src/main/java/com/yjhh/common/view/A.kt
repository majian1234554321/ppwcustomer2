package com.yjhh.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class A @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    init {
        in1(context, attrs, defStyleAttr)
    }

    private fun in1(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {}
}
