package com.yjhh.loginmodule.view

import com.yjhh.loginmodule.bean.LoginBean
import java.util.*

interface RegistView  {
    fun registSuccess(date: LoginBean)

    fun registFault(registFaultMessage: String)

    fun sendSMSSuccess(date: LoginBean)
    fun sendSMSFault(message: String)
}