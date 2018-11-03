package com.yjhh.ppwcustomer.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.ActivityCollector
import com.yjhh.loginmodule.ui.LoginActivity
import com.yjhh.ppwcustomer.CurrentApplication
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_main.*
import com.bigkoo.pickerview.listener.OnOptionsSelectListener

import com.bigkoo.pickerview.view.OptionsPickerView
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.ppwcustomer.bean.ProvinceBean
import com.yjhh.ppwcustomer.bean.ProvinceBean2
import com.yjhh.ppwcustomer.bean.TabEntity
import com.yjhh.ppwcustomer.ui.fragment.Main1Fragment
import com.yjhh.ppwcustomer.ui.fragment.Main2Fragment
import com.yjhh.ppwcustomer.ui.fragment.Main3Fragment
import com.yjhh.ppwcustomer.ui.fragment.Main4Fragment
import java.util.*
import kotlin.collections.ArrayList


@Route(path = "/mainActivity/main")
class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("首页", "消息", "联系人", "更多")
    private val mIconUnselectIds = intArrayOf(
        R.mipmap.tab_home_unselect,
        R.mipmap.tab_speech_unselect,
        R.mipmap.tab_contact_unselect,
        R.mipmap.tab_more_unselect
    )
    private val mIconSelectIds = intArrayOf(
        R.mipmap.tab_home_select,
        R.mipmap.tab_speech_select,
        R.mipmap.tab_contact_select,
        R.mipmap.tab_more_select
    )

    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        ctl_layout.setTabData(mTabEntities)

        val fragment1 = Main1Fragment()
        val fragment2 = Main2Fragment()
        val fragment3 = Main3Fragment()
        val fragment4 = Main4Fragment()
        ctl_layout.currentTab = 0

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment1).commit()

        ctl_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                when (position) {
                    0 -> {
//                        ARouter.getInstance()
//                            .build("/LoginActivity/Login")
//                            .withString("name", "老王")
//                            .withInt("age", 23)
//                            .navigation(this@MainActivity)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment1).commit()

                    }

                    1 -> {
                        //  startActivity(Intent(this@MainActivity, UploadActivity::class.java))
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment2).commit()
                    }


                    2 -> {
                        //   showPickerView()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment3).commit()
                    }

                    3 -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frameLayout, fragment4).commit()

                    }

                    else -> {
                    }
                }

            }

            override fun onTabReselect(position: Int) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }


    private fun showPickerView() {// 弹出选择器


        val options1Items = ArrayList<ProvinceBean>()
        val options2Items = ArrayList<ArrayList<ProvinceBean>>()
        val options3Items = ArrayList<ArrayList<ArrayList<ProvinceBean>>>()

        CurrentApplication.provinceBean.forEachIndexed { index, provinceBean2 ->

            val list2Item = ArrayList<ProvinceBean.CityBean>()
            provinceBean2.node.forEachIndexed { index, nodeBeanX ->
                val list3Item = ArrayList<ProvinceBean.CityBean.AreaBean>()
                nodeBeanX.node.forEachIndexed { index, nodeBean ->
                    list3Item.add(ProvinceBean.CityBean.AreaBean(nodeBean.name, nodeBean.code))
                }
                list2Item.add(ProvinceBean.CityBean(nodeBeanX.name, nodeBeanX.code, list3Item))
            }

            options1Items.add(ProvinceBean(provinceBean2.name, provinceBean2.code, list2Item))
        }





        for (i in 0 until options1Items.size) {//遍历省份
            val CityList = ArrayList<ProvinceBean>()//该省的城市列表（第二级）
            val Province_AreaList = ArrayList<ArrayList<ProvinceBean>>()//该省的所有地区列表（第三极）

            for (c in 0 until options1Items.get(i).cityBeans.size) {//遍历该省份的所有城市
                val CityName = options1Items.get(i).cityBeans[c].cityName
                val CityCode = options1Items.get(i).cityBeans.get(c).cityCode


                CityList.add(ProvinceBean(CityName, CityCode))//添加城市
                val City_AreaList = ArrayList<ProvinceBean>()//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (options1Items.get(i).cityBeans.get(c).areaBean == null || options1Items.get(i).cityBeans.get(c).areaBean.size == 0) {
                    City_AreaList.add(ProvinceBean())
                } else {

                    for (x in 0 until options1Items.get(i).cityBeans.get(c).areaBean.size) {
                        City_AreaList.add(
                            //options1Items.get(i).cityBeans.get(c).areaBean
                            ProvinceBean(
                                options1Items.get(i).cityBeans.get(c).areaBean.get(x).areaName,
                                options1Items.get(i).cityBeans.get(c).areaBean.get(x).areaCode
                            )
                        )
                    }

                }
                Province_AreaList.add(City_AreaList)//添加该省所有地区数据
            }

            options2Items.add(CityList)

            options3Items.add(Province_AreaList)
        }

        val pvOptions = OptionsPickerBuilder(this,
            OnOptionsSelectListener { options1, options2, options3, v ->
                //  返回的分别是三个级别的选中位置
                val tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2).pickerViewText +
                        options3Items.get(options1).get(options2).get(options3).pickerViewText

                Toast.makeText(this@MainActivity, tx, Toast.LENGTH_SHORT).show()
            }).setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .isDialog(true)
            .build<ProvinceBean>()


        // pvOptions.setPicker(list1)//一级选择器
        pvOptions.setPicker(
            options1Items,
            options2Items as List<MutableList<ProvinceBean>>?,
            options3Items as List<MutableList<MutableList<ProvinceBean>>>?

        )
        pvOptions.show(ctl_layout, true)
    }


    private var firstTime: Long = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            val secondTime = System.currentTimeMillis()
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                firstTime = secondTime
                return true
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }


}
