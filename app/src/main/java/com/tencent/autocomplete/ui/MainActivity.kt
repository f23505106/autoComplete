package com.tencent.autocomplete.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.tencent.autocomplete.R
import com.tencent.autocomplete.db.DataBase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test_button.setOnClickListener {
            //94->中国 10086 %号表示模糊查询
            var x = DataBase.getDatabase(this).detailDao().findT9("%94%")
            Log.d("fgt","size:"+x.size)

            for (xx in x){
                Log.d("fgt","phone name:"+xx.name+"  number:"+xx.number)
            }

        }

        search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                Log.d("fgt",p0.toString())
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        DataBase.getDatabase(this).close()
    }
}
