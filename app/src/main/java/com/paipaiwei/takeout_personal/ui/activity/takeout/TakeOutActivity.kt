package com.paipaiwei.takeout_personal.ui.activity.takeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yjhh.common.base.BaseActivity
import com.paipaiwei.takeout_personal.R
import kotlinx.android.synthetic.main.activity_take_out.*

class TakeOutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_out)

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

    }
}
