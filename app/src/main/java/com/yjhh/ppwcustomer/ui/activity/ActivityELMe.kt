package com.yjhh.ppwcustomer.ui.activity


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast


import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.R.id.*

import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import com.yjhh.ppwcustomer.bean.ModelDish
import com.yjhh.ppwcustomer.bean.ModelDishMenu
import com.yjhh.ppwcustomer.bean.ModelShopCart
import com.yjhh.ppwcustomer.ui.customview.RxDialogShopCart

import com.yjhh.ppwcustomer.ui.fragment.Main2_2Fragment
import com.yjhh.ppwcustomer.ui.fragment.Main2_3Fragment
import com.yjhh.ppwcustomer.ui.fragment.OrderDishesFragment
import kotlinx.android.synthetic.main.activity_elme.*

import java.util.ArrayList

class ActivityELMe : BaseActivity(), View.OnClickListener, RxDialogShopCart.ShopCartDialogImp,
    OrderDishesFragment.ActionListener {

    override fun actionEvent(username: String?, password: String?) {


        if (mModelShopCart.shoppingAccount > 0) {
            shopping_cart_total_num.text = mModelShopCart.shoppingAccount.toString()

            shopping_cart_total_num.visibility = View.VISIBLE

            tv_shopping_cart_total.text = "¥ ".plus(mModelShopCart.shoppingTotalPrice)

            tv_shopping_cart_total.visibility = View.VISIBLE
        } else {
            tv_shopping_cart_total.visibility = View.GONE
            shopping_cart_total_num.visibility = View.GONE
        }


    }

    override fun dialogDismiss() {
        orderDishesFragment.showTotalPrice()

        if (mModelShopCart.shoppingAccount > 0) {
            shopping_cart_total_num.text = mModelShopCart.shoppingAccount.toString()

            shopping_cart_total_num.visibility = View.VISIBLE

            tv_shopping_cart_total.text = "¥ ".plus(mModelShopCart.shoppingTotalPrice)

            tv_shopping_cart_total.visibility = View.VISIBLE
        } else {
            tv_shopping_cart_total.visibility = View.GONE
            shopping_cart_total_num.visibility = View.GONE
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.shopping_cart_layout -> {
                if (mModelShopCart.shoppingAccount > 0) {
                    val dialog = RxDialogShopCart(this, mModelShopCart, R.style.cartdialog)
                    val window = dialog.window
                    dialog.shopCartDialogImp = this
                    dialog.setCanceledOnTouchOutside(true)
                    dialog.setCancelable(true)
                    dialog.show()
                    val params = window!!.attributes
                    params.width = WindowManager.LayoutParams.MATCH_PARENT
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT
                    params.gravity = Gravity.BOTTOM
                    params.dimAmount = 0.5f
                    window.attributes = params
                }
            }

            R.id.tv_pay -> {
                Toast.makeText(this, "zhifu", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    lateinit var orderDishesFragment: OrderDishesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elme)

        val mTitles = arrayOf("点菜", "评价", "商家")
        initDatas()

        val mFragments = ArrayList<BaseFragment>()


        orderDishesFragment = OrderDishesFragment(mModelShopCart, mModelDishMenuList)

        mFragments.add(orderDishesFragment)
        mFragments.add(Main2_2Fragment())
        mFragments.add(Main2_3Fragment())

        mViewPager.adapter = Main2ViewPagerAdapter(supportFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

        shopping_cart_total_num.text = "0"
        shopping_cart_total_num.visibility = View.GONE

        shopping_cart_layout.setOnClickListener(this)

        tv_pay.setOnClickListener(this)

    }

    var mModelShopCart = ModelShopCart()
    var mModelDishMenuList = ArrayList<ModelDishMenu>()


    fun initDatas() {

        val dishs1 = ArrayList<ModelDish>()
        dishs1.add(ModelDish("面包", 1.0, 10))
        dishs1.add(ModelDish("蛋挞", 1.0, 10))
        dishs1.add(ModelDish("牛奶", 1.0, 10))
        dishs1.add(ModelDish("肠粉", 1.0, 10))
        dishs1.add(ModelDish("绿茶饼", 1.0, 10))
        dishs1.add(ModelDish("花卷", 1.0, 10))
        dishs1.add(ModelDish("包子", 1.0, 10))
        val breakfast = ModelDishMenu("早点", dishs1)

        val dishs2 = ArrayList<ModelDish>()
        dishs2.add(ModelDish("粥", 1.0, 10))
        dishs2.add(ModelDish("炒饭", 1.0, 10))
        dishs2.add(ModelDish("炒米粉", 1.0, 10))
        dishs2.add(ModelDish("炒粿条", 1.0, 10))
        dishs2.add(ModelDish("炒牛河", 1.0, 10))
        dishs2.add(ModelDish("炒菜", 1.0, 10))
        val launch = ModelDishMenu("午餐", dishs2)

        val dishs3 = ArrayList<ModelDish>()
        dishs3.add(ModelDish("淋菜", 1.0, 10))
        dishs3.add(ModelDish("川菜", 1.0, 10))
        dishs3.add(ModelDish("湘菜", 1.0, 10))
        dishs3.add(ModelDish("粤菜", 1.0, 10))
        dishs3.add(ModelDish("赣菜", 1.0, 10))
        dishs3.add(ModelDish("东北菜", 1.0, 10))
        val evening = ModelDishMenu("晚餐", dishs3)

        val dishs4 = ArrayList<ModelDish>()
        dishs4.add(ModelDish("淋菜", 1.0, 10))
        dishs4.add(ModelDish("川菜", 1.0, 10))
        dishs4.add(ModelDish("湘菜", 1.0, 10))
        dishs4.add(ModelDish("湘菜", 1.0, 10))
        dishs4.add(ModelDish("湘菜1", 1.0, 10))
        dishs4.add(ModelDish("湘菜2", 1.0, 10))
        dishs4.add(ModelDish("湘菜3", 1.0, 10))
        dishs4.add(ModelDish("湘菜4", 1.0, 10))
        dishs4.add(ModelDish("湘菜5", 1.0, 10))
        dishs4.add(ModelDish("湘菜6", 1.0, 10))
        dishs4.add(ModelDish("湘菜7", 1.0, 10))
        dishs4.add(ModelDish("湘菜8", 1.0, 10))
        dishs4.add(ModelDish("粤菜", 1.0, 10))
        dishs4.add(ModelDish("赣菜", 1.0, 10))
        dishs4.add(ModelDish("东北菜", 1.0, 10))
        val menu1 = ModelDishMenu("夜宵", dishs4)

        mModelDishMenuList.add(breakfast)
        mModelDishMenuList.add(launch)
        mModelDishMenuList.add(evening)
        mModelDishMenuList.add(menu1)
    }


}
