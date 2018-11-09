package com.yjhh.ppwcustomer.ui.activity

import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.SearchAdapter
import com.yjhh.ppwcustomer.adapter.SearchContentAdapter
import com.yjhh.ppwcustomer.db.AppDataBase
import com.yjhh.ppwcustomer.db.entity.HistoricalModel
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener2
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {


    lateinit var  mAdapter: SearchContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val list = ArrayList<String>()
        list.add("A")
        list.add("BB")
        list.add("CCC")
        list.add("DDDD")
        list.add("EEEEE")
        list.add("AFFFF")






        recyclerView.layoutManager = LinearLayoutManager(this)
        val historyadapter = SearchAdapter(this, list, history_flowlayout)
        history_flowlayout.adapter = historyadapter
        history_flowlayout.setOnTagClickListener { arg1, arg2, arg3 ->
            Toast.makeText(this@SearchActivity, list.get(arg2), Toast.LENGTH_SHORT).show();
            true

        }



        tv_history.setOnClickListener {


        }




        hot_flowlayout.adapter = SearchAdapter(this, list, hot_flowlayout)


        val dis = RxTextView.textChanges(et_search).subscribe({


                ll0.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                mAdapter = SearchContentAdapter(this, list)
                recyclerView.adapter = mAdapter



        }, {

        })


        mAdapter?.setOnItemClick(object : OnItemClickListener2<String>() {
            override fun onItemClick(vh: RecyclerView.ViewHolder?, data: MutableList<String>?, position: Int) {
                Toast.makeText(this@SearchActivity, data?.get(position), Toast.LENGTH_SHORT).show()

                val historicalModel = HistoricalModel(data?.get(position), data?.get(position))
                AppDataBase
                    .getInstance(this@SearchActivity)
                    .historicalDao
                    .insertAll(historicalModel)
                Toast.makeText(this@SearchActivity, data?.get(position), Toast.LENGTH_SHORT).show()

            }

        })

    }
}
