package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class Slide4Adapter(var context:Context) :RecyclerView.Adapter<Slide4Adapter.ViewHolder>() {
    var mList=ArrayList<String>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvItem=itemView.findViewById<TextView>(R.id.tvSlideItem)
    }

    fun setDataList(dataList:ArrayList<String>,refresh:Boolean){
        if (refresh){
            mList.clear()
        }
        mList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slide4_item,parent,false))

    override fun getItemCount()=mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text=mList[position]

        holder.itemView.setOnClickListener {
            Toast.makeText(context,"点击了条目${holder.adapterPosition}", Toast.LENGTH_SHORT).show()
        }
    }


}