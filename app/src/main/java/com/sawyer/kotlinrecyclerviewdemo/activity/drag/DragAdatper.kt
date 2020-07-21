package com.sawyer.kotlinrecyclerviewdemo.activity.drag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R


class DragAdatper(val context: Context, var list:ArrayList<String>) : RecyclerView.Adapter<DragAdatper.DragViewHolder>(),ItemTouchHelperListener{

    inner class DragViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView=itemView.findViewById<TextView>(R.id.itemTv);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragAdatper.DragViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.click_item,parent,false)
        return DragViewHolder(view)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: DragViewHolder, position: Int) {
        holder.textView.text=list[position]
    }

    //条目拖动的回调
    override fun onItemMove(fromPosition: Int, goPosition: Int) {
        val pre=list.removeAt(fromPosition)
        list.add( goPosition,pre)
        notifyItemMoved(fromPosition,goPosition)
    }

}