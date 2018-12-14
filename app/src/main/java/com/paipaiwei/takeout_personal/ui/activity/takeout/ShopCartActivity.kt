package com.paipaiwei.takeout_personal.ui.activity.takeout

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseActivity
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.ShopCartAdapter
import com.paipaiwei.takeout_personal.bean.DishBean
import com.paipaiwei.takeout_personal.bean.ModelDish
import com.paipaiwei.takeout_personal.bean.ModelShopCart
import kotlinx.android.synthetic.main.activity_shop_cart.*


@Route(path = "/ShopCartActivity/ShopCart")
class ShopCartActivity : BaseActivity() {


    @Autowired
    @JvmField
    var bean: ModelShopCart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)


        var list  =   ArrayList<DishBean>()
        if (bean!=null){

            bean?.shoppingSingleMap?.entries?.forEach {
                list.add(DishBean(it.key,it.value))
            }

        }

         recyclerView.adapter = ShopCartAdapter( list)

        tv_count.text = "合计 ￥${bean?.shoppingAccount}"


        tv_commit.setOnClickListener {
            startActivity(Intent(this, ConfirmOrderActivity::class.java))

        }
    }
}
