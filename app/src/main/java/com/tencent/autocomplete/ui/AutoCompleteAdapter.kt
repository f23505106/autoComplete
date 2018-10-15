package com.tencent.autocomplete.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.tencent.autocomplete.R
import com.tencent.autocomplete.db.DetailEntity



/**
 * Created by timfeng on 2018/10/15.
 */
class AutoCompleteAdapter(private val mContext: Context,private val mResourceId:Int):ArrayAdapter<DetailEntity>(mContext,mResourceId) {

    private val tag ="fgt"

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var retView= if(convertView == null) {
            val inflater = (mContext as MainActivity).layoutInflater
            inflater.inflate(mResourceId, parent, false)
        }else{convertView}
        var data = getItem(position)
        retView.findViewById<TextView>(R.id.phone_name).text = data.name
        retView.findViewById<TextView>(R.id.phone_number).text = data.number
        return retView
    }
    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val result = FilterResults()
                result.values = p0
                result.count = p0?.length?:0
                return result
            }
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }


}