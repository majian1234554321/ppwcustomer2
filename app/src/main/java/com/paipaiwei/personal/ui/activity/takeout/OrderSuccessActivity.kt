package com.paipaiwei.personal.ui.activity.takeout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.common.base.BaseActivity
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.activity_order_success.*

class OrderSuccessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        tv_check.setOnClickListener {
            startActivity(Intent(this, OrderDetailActivity::class.java))
        }
    }
}
