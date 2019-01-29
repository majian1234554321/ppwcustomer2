package com.paipaiwei.personal.ui.activity.onepay

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.ui.customview.ARSetupView
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.onepayfragment.*

class OnePayFragment : BaseFragment(), OnePayService.OnePayView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_a -> {
                val dialog = OnePayDialogFragment()
                dialog.show(childFragmentManager, "TAG")
                dialog.setOnDialogClick(object : OnePayDialogFragment.OnDialogClickListener {
                    override fun onDialogClick(value: Int) {
                        present?.oneMoneyBuy(mb_aid, value.toString(), "oneMoneyBuy")
                        dialog.dismiss()
                    }

                })

            }
            R.id.mb_b -> {
                val dialog = OnePayDialogFragment()
                dialog.show(childFragmentManager, "TAG")

                dialog.setOnDialogClick(object : OnePayDialogFragment.OnDialogClickListener {
                    override fun onDialogClick(value: Int) {
                        present?.oneMoneyBuy(mb_bid, value.toString(), "oneMoneyBuy")
                        dialog.dismiss()
                    }

                })

            }
            else -> {
            }
        }
    }

    var mb_aid = ""
    var mb_bid = ""


    override fun PaiValue(model: String?, flag: String) {
        val gson = Gson()

        if ("userProp" == flag) {
            val bean = gson.fromJson<OnePayBean>(model, OnePayBean::class.java)






            if (bean.propA != null) {
                mb_aid = bean.propA.id
                propa_title.text = bean.propA.name
                propa_count.text = "数量：${bean.propA.quantity}"
            } else {
                mcva.visibility = View.GONE
            }


            if (bean.propB != null) {
                mb_bid = bean.propB.id
                propb_title.text = bean.propB.name
                propb_count.text = "数量：${bean.propB.quantity}"
            } else {
                mcvb.visibility = View.GONE
            }


        }

        if ("oneMoneyBuy" == flag) {


            start(OnePayMoneyFragment.newInstance(model, "道具购买"))
        }


    }

    override fun onFault(errorMsg: String?) {

        Toast.makeText(mActivity, errorMsg, Toast.LENGTH_LONG).show()

    }


    override fun getLayoutRes(): Int = R.layout.onepayfragment

    var present: OnePayPresent? = null


    override fun initView() {
        ImmersionBar.setTitleBar(mActivity, tbv_title)

        present = OnePayPresent(mActivity, this)
        present?.userProp("userProp")



        arrayOf(mb_a, mb_b).forEach {
            it.setOnClickListener(this)
        }


    }


    class OnePayDialogFragment() : DialogFragment() {
        interface OnDialogClickListener {
            fun onDialogClick(value: Int)
        }

        var listener: OnDialogClickListener? = null

        fun setOnDialogClick(listener: OnDialogClickListener) {
            this.listener = listener
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            val builder = AlertDialog.Builder(activity)
            val view = View.inflate(activity, R.layout.onepaydialog, null)

            val arsv = view.findViewById<ARSetupView>(R.id.arsv)


            view.findViewById<MaterialButton>(R.id.mb_pay).setOnClickListener {
                listener?.onDialogClick(arsv.count)
            }



            builder.setView(view)
            val dialog = builder.create()
            return dialog
        }


        override fun onStart() {
            super.onStart()
            val win = dialog.window
            // 一定要设置Background，如果不设置，window属性设置无效
            win!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            dialog.window.setBackgroundDrawableResource(android.R.color.transparent);


            val dm = DisplayMetrics()
            activity!!.windowManager.defaultDisplay.getMetrics(dm)

            val params = win.attributes
            params.gravity = Gravity.CENTER
            // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕

            params.width = (dm.widthPixels * 0.80).toInt()
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT


            Log.i("ProofreadingDialog", params.width.toString())

            win.attributes = params
        }


    }


}