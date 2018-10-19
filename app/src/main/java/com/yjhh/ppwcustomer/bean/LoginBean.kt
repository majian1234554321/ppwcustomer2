package com.yjhh.ppwcustomer.bean

import com.yjhh.ppwcustomer.api.BaseResponse


data class LoginBean( var name: String,var data:Log2): BaseResponse<Log2>()

data class Log2(var name: String)