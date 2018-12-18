package com.paipaiwei.personal.view


import com.paipaiwei.personal.bean.LoginBean
import java.util.*

interface RegistView  {
    fun registSuccess(date: LoginBean?)

    fun registSuccess2(date: String?)

    fun registFault(registFaultMessage: String)

    fun sendSMSSuccess(date: LoginBean?)
    fun sendSMSFault(message: String)
}