package com.yjhh.ppwcustomer.ui.fragment

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseFragment
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.present.RegByAccountPresent
import com.yjhh.loginmodule.view.RegistView
import com.yjhh.ppwcustomer.R
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.registfragment.*
import java.util.concurrent.TimeUnit


class RegistFragment : BaseFragment(), View.OnClickListener, RegistView {

    val identity = "0"//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "2"
    val refId = "";//推荐人ID/phone

    override fun registSuccess(date: LoginBean) {

        Toast.makeText(context, "用户注册成功", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }

    override fun registFault(registFaultMessage: String) {
        Toast.makeText(context, "用户注册失败$registFaultMessage", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSSuccess(date: LoginBean) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败", Toast.LENGTH_SHORT).show()
    }


    val regByAccountPresent = RegByAccountPresent(context, this)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_verifyCode -> {
                val disposable = mObservableCountTime.subscribe(mConsumerCountTime)
                compositeDisposable.add(disposable)
            }


            R.id.bt_register -> {
                regByAccountPresent.regByAccount(
                    et_phone.text.toString()
                    , et_password.text.toString(), et_verifyCode.text.toString()
                    , identity, refId

                )
            }

            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.registfragment


    val MAX_COUNT_TIME = 60L

    private lateinit var mObservableCountTime: Observable<Long>
    private lateinit var mConsumerCountTime: Consumer<Long>
    override fun initView(rootView: View?) {

        tv_verifyCode.setOnClickListener(this)
        bt_register.setOnClickListener(this)

        mObservableCountTime = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap(Function<Any, ObservableSource<Boolean>> {


                val phone = et_phone.text.toString()
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(activity, "手机号码不能为空", Toast.LENGTH_SHORT).show()
                    return@Function Observable.empty()
                } else {
                    return@Function Observable.just(true)
                }

            })

            .flatMap(Function<Any, ObservableSource<Long>> {
                RxView.enabled(tv_verifyCode).accept(false)
                RxTextView.text(tv_verifyCode).accept("剩余 $MAX_COUNT_TIME 秒")

                return@Function Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                    .take(MAX_COUNT_TIME)
                    //将递增数字替换成递减的倒计时数字
                    .map { aLong -> MAX_COUNT_TIME - (aLong + 1); }
            }
            )


            //切换到 Android 的主线程。
            .observeOn(AndroidSchedulers.mainThread())

        mConsumerCountTime = Consumer<Long>() {
            if (it == 0L) {
                RxView.enabled(tv_verifyCode).accept(true)
                RxTextView.text(tv_verifyCode).accept("发送验证码")
                regByAccountPresent.sendSms(TYPE, et_phone.text.toString())
            } else {
                RxTextView.text(tv_verifyCode).accept("剩余 $it 秒")
            }
        };


    }


}