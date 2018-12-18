package com.paipaiwei.personal.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.ConstellationAdapter

import kotlinx.android.synthetic.main.selectdistrictfragment.*
import me.yokeyword.fragmentation.ISupportFragment
import java.util.*

class SelectDistrictFragment : BaseFragment() {

    private val constellations =
        arrayOf("全城", "江汉区", "武昌区", "江岸区", "硚口区", "汉阳区", "洪山区", "蔡甸区", "黄陂区")

    override fun getLayoutRes(): Int = R.layout.selectdistrictfragment
    override fun initView() {
        var constellationAdapter = ConstellationAdapter(context, Arrays.asList(*constellations))
        gridView.adapter = constellationAdapter
        tv_currentLocation.text = "当前地址:${com.yjhh.common.Constants.district}"

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->



           val intent =  Intent()
            intent.putExtra("location",constellations[position])
            mActivity.setResult(RESULT_OK,intent)

            mActivity.finish()
        }
    }
}
