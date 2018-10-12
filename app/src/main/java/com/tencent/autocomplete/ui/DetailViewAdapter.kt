package com.tencent.autocomplete.ui
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tencent.autocomplete.R
import com.tencent.autocomplete.data.PhoneNamePair

/**
 * Created by timfeng on 2018/10/12.
 */


class DetailViewAdapter ( private val mData:List<PhoneNamePair>)
    :RecyclerView.Adapter<DetailViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var mNameTv = itemView?.findViewById<TextView>(R.id.phone_name)
        var mPhoneTv = itemView?.findViewById<TextView>(R.id.phone_number)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.detail_item, parent, false) as View
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var pair = mData[position]
        holder?.mNameTv?.text = pair.mName
        holder?.mPhoneTv?.text = pair.mNumber
    }

    override fun getItemCount(): Int {
        return mData.size
    }


}