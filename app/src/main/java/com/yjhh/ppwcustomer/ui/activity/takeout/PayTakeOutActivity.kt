package com.yjhh.ppwcustomer.ui.activity.takeout

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.common.base.BaseActivity
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_pay_take_out.*

class PayTakeOutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_take_out)


        tv_pay.setOnClickListener {


            startActivity(Intent(this,PaySuccessActivity::class.java))


        }

    }
}
