package com.sawyer.kotlinrecyclerviewdemo.activity.click

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class ClickAdapter(val context: Context, val list:List<String>,
                   var listener: onMineItemClickListener): RecyclerView.Adapter<ClickAdapter.ClickViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClickViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.click_item,parent,false)
        return ClickViewHolder(view)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ClickViewHolder, position: Int) {
        holder.textView.text=list[position]
        //第一种写法：直接在adapter里写
        /*holder.itemView.setOnClickListener {
            Toast.makeText(context,"点击的是第${position}个条目",Toast.LENGTH_SHORT).show()
        }*/
        //第二种写法：传递点击事件到Activity
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    inner class ClickViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView=itemView.findViewById<TextView>(R.id.itemTv);
    }

    interface onMineItemClickListener{
        fun onItemClick(position:Int)
    }

}
