package com.tencent.autocomplete.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tencent.autocomplete.R


/**
 * Created by timfeng on 2018/10/12.
 * test
 */
class YellowPageDetailView: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
    }

    override fun onResume() {
        super.onResume()
        Log.d("fgt","onResume")
    }
}