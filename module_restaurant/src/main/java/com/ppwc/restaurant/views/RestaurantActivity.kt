package com.ppwc.restaurant.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment


@Route(path = "/RestaurantActivity/Restaurant")
class RestaurantActivity : BaseActivity() {


    @Autowired
    @JvmField
    var displayTab: String? = null

    @Autowired
    @JvmField
    var id: String? = "0"

    @Autowired
    @JvmField
    var type: String? = "XX"

    @Autowired
    @JvmField
    var typeValue: String? = "XX"



    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)


        displayTab = intent.getStringExtra("displayTab")


        when (displayTab) {
            "RestaurantInFragment" -> {
                loadRootFragment(R.id.frameLayout, RestaurantInFragment.newInstance(type,typeValue))
            }

            "ConsumptionLogFragment" -> {
                loadRootFragment(R.id.frameLayout, ConsumptionLogFragment.newInstance(id))
            }

            "RestaurantOrderDetailsFragment" ->{

                loadRootFragment(R.id.frameLayout, RestaurantOrderDetailsFragment.newInstance(id))
            }

            else -> {
                loadRootFragment(R.id.frameLayout, RestaurantHomeFragment.newInstance(id))

            }

        }


    }

}