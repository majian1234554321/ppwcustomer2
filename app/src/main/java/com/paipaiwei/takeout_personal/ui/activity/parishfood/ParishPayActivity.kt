package com.paipaiwei.takeout_personal.ui.activity.parishfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paipaiwei.takeout_personal.R
import kotlinx.android.synthetic.main.activity_parish_pay.*

class ParishPayActivity : AppCompatActivity() {

    var typePay = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parish_pay)

        rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_aliPay -> {
                    typePay = "0"
                }

                R.id.rb_wxPay -> {
                    typePay = "1"
                }
                else -> {
                }
            }
        }


        tv_pay.setOnClickListener {

        }

    }
}
