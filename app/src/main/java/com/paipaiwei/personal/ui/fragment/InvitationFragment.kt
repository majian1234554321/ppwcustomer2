package com.paipaiwei.personal.ui.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.ArrayMap
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.ui.activity.onepay.OnePayFragment
import com.ppwc.restaurant.views.RestaurantHomeFragment
import com.tencent.tauth.IUiListener
import com.tencent.tauth.UiError
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.ShareUtils
import com.yjhh.common.view.TitleBarView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.invitationfragment.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


class InvitationFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.invitationfragment

    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)

        ApiServices.getInstance().create(InvitationService::class.java).invite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("invite", response)

                    val model = Gson().fromJson<InvitationBean>(response, InvitationBean::class.java)

                    ImageLoaderUtils.loadCircle(
                        mActivity,
                        iv_image,
                        model.imageUrl,
                        R.drawable.icon_place_pai,
                        R.drawable.icon_place_pai
                    )
                    tv_name.text = model.nickName
                    tv_phone.text = model.mobile
                    tv_people2.text = model.num.toString()
                    progressBar.progress = model.num
                    tv_people.text = "${model.num}/${model.total} 人"


                    if (model.ifEnabled) {
                        mb_access.isEnabled = true
                        mb_access.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F13716"))
                    } else {
                        mb_access.isEnabled = false
                        mb_access.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                    }


                    tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
                        override fun setOnRightClick() {
                            ShareUtils.dialog(mActivity, MyIUiListener(), "邀请有礼", model.shareUrl, "邀请有礼")
                        }

                    })


                    tv_rule.setOnClickListener {
                        start(BackViewFragment.newInstance(model.ruleUrl, "InvitationFragment"))
                    }

                    mb_access.setOnClickListener {


                        val map = ArrayMap<String, String>()
                        map["id"] = model.id

                        ApiServices.getInstance().create(InvitationService::class.java).inviteAward(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : ProcessObserver2(mActivity) {
                                override fun onFault(message: String) {
                                    Log.i("inviteAward", message)
                                    Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
                                }

                                override fun processValue(response: String?) {

                                    Log.i("inviteAward", response)

                                    val modelX = Gson().fromJson<InvitationBean>(response, InvitationBean::class.java)


                                    val dialogFragment = InvitationDialogFragment(mActivity,modelX.title,modelX.imageUrl)
                                    dialogFragment.show(childFragmentManager, "dialogFragment")
                                    dialogFragment.setOnClickListener(object :
                                        InvitationDialogFragment.OnDialogClickListener {
                                        override fun onDialogClick() {
                                            start(OnePayFragment())
                                            dialogFragment.dismiss()
                                        }

                                    })
                                }
                            })


                    }

                }

                override fun onFault(message: String) {
                    Log.i("invite", message)
                }

            })

    }

    class MyIUiListener : IUiListener {
        override fun onComplete(p0: Any?) {

        }

        override fun onCancel() {

        }

        override fun onError(p0: UiError?) {

        }

    }

    interface InvitationService {

        @POST("register/invite")
        fun invite(): Observable<ResponseBody>

        @FormUrlEncoded
        @POST("register/inviteAward")
        fun inviteAward(@FieldMap map: Map<String, String>): Observable<ResponseBody>
    }

    data class InvitationBean(
        val id: String,
        val ifEnabled: Boolean,
        val imageUrl: String,
        val mobile: String,
        val nickName: String,
        val num: Int,
        val range: Int,
        val ruleUrl: String,
        val shareUrl: String,
        val total: Int, val btnText: String, val code: String, val ifShowBtn: Boolean, val title: String,
        val type: Int



    )

}


