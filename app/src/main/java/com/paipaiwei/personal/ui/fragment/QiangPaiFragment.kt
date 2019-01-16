package com.paipaiwei.personal.ui.fragment

import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.qiangpaifragment.*


class QiangPaiFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.qiangpaifragment

    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)
    }
}