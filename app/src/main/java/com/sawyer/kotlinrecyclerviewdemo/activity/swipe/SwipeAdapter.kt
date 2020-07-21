package com.sawyer.kotlinrecyclerviewdemo.activity.swipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class SwipeAdapter (val context: Context, var list:ArrayList<String>) : RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder>(),
    ItemTouchHelperListener {


    inner class SwipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView=itemView.findViewById<TextView>(R.id.itemTv);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeAdapter.SwipeViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.click_item,parent,false)
        return SwipeViewHolder(view)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: SwipeAdapter.SwipeViewHolder, position: Int) {
        holder.textView.text=list[position]
    }

    //条目拖动的回调
    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}