package com.sawyer.kotlinrecyclerviewdemo.activity.sticky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class StickyAdapter(val list:List<StickyBean>): RecyclerView.Adapter<StickyAdapter.ViewHolder>() {

    companion object{
        const val FIRST_STICKY_VIEW=1
        const val HAS_STICKY_VIEW=2
        const val NONE_STICKY_VIEW=3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sticky_item,parent,false))
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean=list[position]
        holder.tvTeam.text=bean.team
        if (position==0){
            holder.tvArea.visibility=View.VISIBLE
            holder.tvArea.text=bean.area
            holder.itemView.tag= FIRST_STICKY_VIEW
        }else{
            if (bean.area==list[position-1].area){
                holder.tvArea.visibility=View.GONE
                holder.itemView.tag= NONE_STICKY_VIEW
            }else{
                holder.tvArea.visibility=View.VISIBLE
                holder.tvArea.text=bean.area
                holder.itemView.tag= HAS_STICKY_VIEW
            }

        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var tvArea: TextView =itemView.findViewById(R.id.tvStickyArea)
        var tvTeam: TextView =itemView.findViewById(R.id.tvStickyTeam)
    }

}