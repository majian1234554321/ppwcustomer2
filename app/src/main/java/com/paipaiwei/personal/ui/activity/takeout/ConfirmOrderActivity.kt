package com.paipaiwei.personal.ui.activity.takeout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat.startActivityForResult
import android.view.View
import com.yjhh.common.base.BaseActivity
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.activity_confirm_order.*

class ConfirmOrderActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_coupon -> {
                startActivityForResult(Intent(this, UseCouponActivity::class.java), 10085)
            }

            R.id.iev_remark -> {
                startActivityForResult(Intent(this, OrderRemarkActivity::class.java), 10086)
            }


            R.id.tv_commit -> {
                startActivity(Intent(this, PayTakeOutActivity::class.java))
            }
            else -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)


        val arrays = arrayOf(iev_coupon, iev_remark, tv_commit)

        arrays.forEach {
            it.setOnClickListener(this)
        }

        iev_coupon.setTextContent("无")
        iev_Cashcoupon.setTextContent("无")
        iev_arrayTime.setTextContent("19.00")
        iev_Distribution.setTextContent("￥12")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10085 -> {
                val value = data?.getStringExtra("address")
                tv_commit.text = value
            }

            10086 -> {

            }
            else -> {
            }
        }

    }


}
