package com.yjhh.ppwcustomer.view


import com.yjhh.ppwcustomer.bean.LoginBean
import java.util.*

interface RegistView  {
    fun registSuccess(date: LoginBean?)

    fun registSuccess2(date: String?)

    fun registFault(registFaultMessage: String)

    fun sendSMSSuccess(date: LoginBean?)
    fun sendSMSFault(message: String)
}