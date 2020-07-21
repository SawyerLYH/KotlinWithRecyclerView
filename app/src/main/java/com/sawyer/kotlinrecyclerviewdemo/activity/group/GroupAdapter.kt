package com.sawyer.kotlinrecyclerviewdemo.activity.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class GroupAdapter(val list:List<GroupBean>):RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_item,parent,false))
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvArea.text=list[position].area
        holder.tvTeam.text=list[position].team
        if (position==0){
            holder.tvArea.visibility=View.VISIBLE
        }else{
            //当前的条目地区名与上一个相同，则隐藏
            if (list[position].area==list[position-1].area){
                holder.tvArea.visibility=View.GONE
            }else{
                holder.tvArea.visibility=View.VISIBLE
            }
        }

    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var tvArea:TextView=itemView.findViewById(R.id.tvArea)
        var tvTeam:TextView=itemView.findViewById(R.id.tvTeam)
    }

}