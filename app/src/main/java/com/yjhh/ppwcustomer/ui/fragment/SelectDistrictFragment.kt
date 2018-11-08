package com.yjhh.ppwcustomer.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.ConstellationAdapter
import kotlinx.android.synthetic.main.selectdistrictfragment.*
import java.util.*

class SelectDistrictFragment : BaseFragment() {

    private val constellations =
        arrayOf("全城", "江汉区", "武昌区", "江岸区", "硚口区", "汉阳区", "洪山区", "蔡甸区", "黄陂区")

    override fun getLayoutRes(): Int = R.layout.selectdistrictfragment
    override fun initView() {
        var constellationAdapter = ConstellationAdapter(context, Arrays.asList(*constellations))
        gridView.adapter = constellationAdapter
        tv_currentLocation.text = "当前地址:${com.yjhh.common.Constants.district}"
    }
}
