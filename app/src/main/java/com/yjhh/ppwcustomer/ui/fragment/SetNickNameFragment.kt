package com.yjhh.ppwcustomer.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R


class SetNickNameFragment : BaseFragment() {

    private var mTitle: String? = null


    override fun getLayoutRes(): Int {
        return R.layout.setnicknamefragment
    }

    override fun initView() {
        val args = arguments
        if (args != null) {
            mTitle = args.getString("ARG_TITLE")
        }



//        mBtnModify.setOnClickListener(View.OnClickListener {
//            val bundle = Bundle()
//            bundle.putString(DetailFragment.KEY_RESULT_TITLE, mEtModiyTitle.getText().toString())
//            setFragmentResult(ISupportFragment.RESULT_OK, bundle)
//
//            Toast.makeText(_mActivity, R.string.modify_success, Toast.LENGTH_SHORT).show()
//        })


    }

    companion object {

        fun newInstance(title: String): SetNickNameFragment {
            val args = Bundle()
            val fragment = SetNickNameFragment()
            args.putString("ARG_TITLE", title)
            fragment.arguments = args
            return fragment
        }
    }




}


