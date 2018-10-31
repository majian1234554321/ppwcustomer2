package com.yjhh.ppwcustomer


import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

import org.json.JSONObject

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun addition_isCorrect() :String = runBlocking<String> {

       val job =   launch { // launch new coroutine and keep a reference to its Job
            delay(5000L)
            println("World!")
        }


       job.join()
        "212"

    }


    @Test
    fun AA(){

        println(addition_isCorrect())

    }


}
