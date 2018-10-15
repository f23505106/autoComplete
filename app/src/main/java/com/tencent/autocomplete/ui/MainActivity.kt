package com.tencent.autocomplete.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tencent.autocomplete.R
import com.tencent.autocomplete.db.DataBase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val subject = PublishSubject.create<String>()
    private lateinit  var adapter:AutoCompleteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test_button.setOnClickListener {
            //94->中国 10086 %号表示模糊查询
            val x = DataBase.getDatabase(this).detailDao().findT9("%94%")
            Log.d("fgt","size:"+x.size)
            for (xx in x){
                Log.d("fgt","phone name:"+xx.name+"  number:"+xx.number)
            }

        }
        search.threshold=1
        search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                Log.d("fgt",p0.toString())
                subject.onNext(p0.toString())
            }

        })
        search.setOnEditorActionListener{ v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {false}
        }
        adapter = AutoCompleteAdapter(this,R.layout.detail_item)
        search.setAdapter(adapter)
        initSearchDb()


    }
    private fun initSearchDb(){
        subject.debounce(800, TimeUnit.MILLISECONDS)
            .filter{it.isNotEmpty()}
            .distinctUntilChanged()
            .switchMap{s ->Log.d("fgt","switchMap:"+s); Observable.just(s)}
            .subscribeOn(Schedulers.io())
            .subscribe{s->
                Log.d("fgt","subscribe:"+s)
                //94->中国 10086 %号表示模糊查询
                val x = DataBase.getDatabase(this).detailDao().findT9("%$s%")
                runOnUiThread{
                    adapter.clear()
                    if(x.size>0) {
                        adapter.apply {
                            addAll(x)
                            notifyDataSetChanged()
                        }
                    }else {
                        Toast.makeText(this, "no tip in database", Toast.LENGTH_SHORT).show()
                    }
                }

                Log.d("fgt","size:"+x.size)
                for (xx in x){
                    Log.d("fgt","phone name:"+xx.name+"  number:"+xx.number)
                }
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        DataBase.getDatabase(this).close()
    }
}
