package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class SlideAdapter :RecyclerView.Adapter<SlideAdapter.SlideViewHolder>() {

    var mList=ArrayList<String>()

    inner class SlideViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvSlideItem=itemView.findViewById<TextView>(R.id.tvSlideItem)
        val tvSlideDel=itemView.findViewById<TextView>(R.id.tvSlideDel)
    }

    fun setDataList(dataList:ArrayList<String>){
        this.mList=dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        SlideViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false))

    override fun getItemCount()=mList.size

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.tvSlideItem.text=mList[position]

        if (!holder.tvSlideDel.hasOnClickListeners()){
            holder.tvSlideDel.setOnClickListener {
                mList.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
            }
        }


    }




}