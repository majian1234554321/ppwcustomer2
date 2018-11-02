package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import android.widget.Toast
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.common.utils.GlideImageLoader
import kotlinx.android.synthetic.main.main1fragment.*
import java.util.ArrayList

class Main1Fragment : BaseFragment() {
    override fun initView(rootView: View?) {

        val mImages = ArrayList<String>()
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541064541354&di=38af2318be9cf7d66a7011232dd3d406&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G205134648-51.jpg")
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541064541354&di=38af2318be9cf7d66a7011232dd3d406&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G205134648-51.jpg")
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541064541354&di=38af2318be9cf7d66a7011232dd3d406&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G205134648-51.jpg")
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541064541354&di=38af2318be9cf7d66a7011232dd3d406&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G205134648-51.jpg")


        banner.setImages(mImages)
            .setImageLoader(GlideImageLoader())
            .setDelayTime(5000)
            .start()
        banner.setOnBannerListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            when (it) {
                0 -> {

                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
                else -> {
                }
            }
        }


      //  text.text = SharedPreferencesUtils.getParam(context, "sessionId", "-1").toString()

    }

    override fun getLayoutRes(): Int = R.layout.main1fragment
}