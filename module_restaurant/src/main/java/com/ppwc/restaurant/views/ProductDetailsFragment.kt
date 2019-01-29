package com.ppwc.restaurant.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.ppwc.restaurant.R
import com.ppwc.restaurant.bean.ProductDetailsBean
import com.ppwc.restaurant.ipresent.ShopPresent
import com.ppwc.restaurant.iview.ProductDetailsView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.iview.CommonView
import com.yjhh.common.present.CommonPresent
import com.yjhh.common.utils.GlideLoader
import com.yjhh.common.view.TitleBarView
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.productdetailsfragment.*

class ProductDetailsFragment : BaseFragment(), View.OnClickListener, ProductDetailsView, CommonView {
    override fun onFault(errorMsg: String?, flag: String?) {

    }

    override fun onSuccess(value: String?, flag: String?) {

    }

    override fun onProductDetailsValue(model: ProductDetailsBean) {

        tv_storeName.text = model.shopName
        tv_price.text = model.price
        tv_info.text = model.describe
        tv_count.text = model.zan


        if (model.ifZan) {
            dianzan.setBackgroundResource(R.drawable.zan042x)
        } else {
            dianzan.setBackgroundResource(R.drawable.zan032x)
        }


        if (model.ifCollect) {
            xingxing.setBackgroundResource(R.drawable.xingxing042x)
        } else {
            xingxing.setBackgroundResource(R.drawable.xingxing032x)
        }


        if (model.images != null && model.images.size > 0) {

            val imageList = ArrayList<String>()
            model.images.forEach {
                imageList.add(it.fileUrl)
            }

            banner.setBannerStyle(BannerConfig.NOT_INDICATOR)
            banner.setImages(imageList)
                .setImageLoader(GlideLoader())
                .setDelayTime(10000000)
                .start()

            tv_index.text = "1/${imageList.size}"

            banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    tv_index.text = "${position + 1}/${imageList.size}"
                }

                override fun onPageScrollStateChanged(state: Int) {
                }

            })

        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.xingxing -> {
                xingxing.setBackgroundResource(R.drawable.xingxing032x)
                present?.collect("0", itemId, "collect")  //	类别(0商品 1店铺 2文章)


            }

            R.id.dianzan -> {

                present?.zan(itemId, "zan")
            }

            else -> {

            }
        }
    }


    var present: CommonPresent? = null

    override fun getLayoutRes(): Int = R.layout.productdetailsfragment
    var itemId: String? = ""

    override fun initView() {


        itemId = arguments?.getString("itemId")

        ShopPresent(mActivity, this).product(itemId)


        present = CommonPresent(mActivity, this)


        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {

            }

        })


        arrayOf(xingxing, dianzan).forEach {
            it.setOnClickListener(this)
        }


    }


    companion object {
        fun newInstance(id: String): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val bundle = Bundle()
            bundle.putString("itemId", id)
            fragment.arguments = bundle
            return fragment
        }
    }


}