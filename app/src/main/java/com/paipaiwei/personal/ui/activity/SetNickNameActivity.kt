package com.paipaiwei.personal.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.RxLoginBean
import com.paipaiwei.personal.bean.UserinfoBean
import com.paipaiwei.personal.present.SectionUserPresent
import com.paipaiwei.personal.view.UserInfoView
import kotlinx.android.synthetic.main.activity_set_nick_name.*

class SetNickNameActivity : BaseActivity(), UserInfoView {
    override fun onSuccess(main1bean: UserinfoBean) {
        val intent = Intent()
        setResult(RESULT_OK, intent.putExtra("title", et_nickName.text.toString()))




        finish()
    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_nick_name)

        val value = intent.getStringExtra("title")
        et_nickName.setText(value)

        val present = SectionUserPresent(this, this)



        tv_commit.setOnClickListener {
            present.setNickName(et_nickName.text.toString())
        }
    }
}
