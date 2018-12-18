package com.paipaiwei.personal.ui.fragment

import android.bluetooth.le.AdvertiseData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.yjhh.common.Constants
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.LeftOnClickListener
import com.yjhh.common.listener.RightOnClickListener
import com.yjhh.common.utils.RxBus
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.MyAddressBean
import com.paipaiwei.personal.bean.rxbusbean.RxAddressBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.present.SectionUserPresent
import com.paipaiwei.personal.ui.activity.ActivityContact
import com.paipaiwei.personal.ui.activity.SelectAddressByMapActivity
import com.paipaiwei.personal.view.MyAddressView
import kotlinx.android.synthetic.main.addressaddfragment.*
import me.yokeyword.fragmentation.ISupportFragment


//我的地址增删改
class AddressADUFragment : BaseFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener, MyAddressView {
    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(main1bean: MyAddressBean, flag: String) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        RxBus.default.post(RxAddressBean())
        mActivity?.finish()

    }

    var genderArg = "0"
    var tagsArg = ""
    var idArg = ""
    var longitude = Constants.LONGITUDE

    var latitude = Constants.LATITUDE


    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_label1 -> {
                tagsArg = "家"
            }
            R.id.rb_label2 -> {
                tagsArg = "公司"
            }

            R.id.rb_label3 -> {
                tagsArg = "学校"
            }

            R.id.rb_male -> {
                genderArg = "0"
            }

            R.id.rb_female -> {
                genderArg = "1"
            }

            else -> {
                tagsArg = ""
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_contact -> {
                startActivityForResult(Intent(mActivity, ActivityContact::class.java), 10085)
            }

            R.id.iv_location -> {
                startActivityForResult(Intent(mActivity, SelectAddressByMapActivity::class.java), 10086)
            }

            R.id.tv_commit -> {

                present.editByLocation(
                    idArg, genderArg, et_Contacts.text.toString(),
                    et_phone.text.toString(),
                    et_address.text.toString(),
                    et_text.text.toString(),
                    tagsArg, longitude, latitude, "1"
                )

            }


            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.addressaddfragment

    lateinit var present: SectionUserPresent
    override fun initView() {

        val bean = arguments?.getSerializable("bean") as RxAddressBean
        present = SectionUserPresent(context, this)

//        tbv_title.setLeftOnClick(LeftOnClickListener {
//            mActivity.finish()
//        })
//
//        tbv_title.setRightOnClick(RightOnClickListener {
//            // 删除
//            present.deleteUseraddress(bean.id)
//        })

        if ("ADD" == bean.flag) {
            tbv_title.setTitle("新增地址")
        } else {
            tbv_title.setTitle("修改地址")

            idArg = bean.id
            et_Contacts.setText(bean.userName)
            rg_gender.check(if (0 == bean.gender) R.id.rb_male else R.id.rb_female)
            et_phone.setText(bean.userPhone)
            et_address.setText(bean.address)
            et_text.setText(bean.no)
            rg_label.check(

                when (bean.tags) {
                    "家" -> {
                        R.id.rb_label1
                    }

                    "公司" -> {
                        R.id.rb_label2
                    }

                    "学校" -> {
                        R.id.rb_label3
                    }
                    else -> {
                        -1
                    }
                }

            )


        }

        val view = arrayOf(iv_location, tv_commit, tv_contact)
        view.forEach {
            it.setOnClickListener(this)
        }



        rg_gender.setOnCheckedChangeListener(this)
        rg_label.setOnCheckedChangeListener(this)


    }


    companion object {
        fun newInstance(rxAddressBean: RxAddressBean?): AddressADUFragment {
            val args = Bundle()
            val fragment = AddressADUFragment()
            fragment.arguments = args
            args.putSerializable("bean", rxAddressBean)
            return fragment
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1) {
            when (requestCode) {
                10086 -> {
                    val address = data?.getStringExtra("address")
                    latitude = data?.getStringExtra("latitude")
                    et_address.setText(address)
                }

                10085 -> {

                }

                else -> {
                }
            }
        }


    }


}