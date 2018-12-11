package com.yjhh.ppwcustomer.ui.activity.takeout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.common.base.BaseActivity
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_pay_success.*

class PaySuccessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_success)


        tv_next.setOnClickListener {
            startActivity(Intent(this,OrderSuccessActivity::class.java))
        }


    }
}
