package com.yjhh.ppwcustomer.ui.activity.parishfood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.common.base.BaseActivity
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_parish_buy.*

class ParishBuyActivity : BaseActivity() {


    var totalprice = 0f //消费总额
    var nodiscount = 0f //不参与金额
    var payprice = 0f  // 最终消费金额

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parish_buy)

        tv_commit.setOnClickListener {
            startActivity(Intent(this, ParishPayActivity::class.java))
        }
    }
}
