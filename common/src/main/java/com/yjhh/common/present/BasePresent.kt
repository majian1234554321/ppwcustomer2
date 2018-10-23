package com.yjhh.common.present


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BasePresent {
    fun <T> toSubscribe(o: Observable<T>, s: DisposableObserver<T>) {
        o.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(2)//请求失败重连次数
            .subscribe(s)

    }
}