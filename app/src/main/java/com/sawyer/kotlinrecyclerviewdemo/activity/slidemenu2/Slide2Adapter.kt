package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.sawyer.kotlinrecyclerviewdemo.R
import javax.security.auth.login.LoginException

//开源项目 :daimajia /AndroidSwipeLayout
class Slide2Adapter :RecyclerSwipeAdapter<Slide2Adapter.SlideViewHolder>() {

    var mList=ArrayList<String>()

    var lastOpenPos=-1

    override fun getSwipeLayoutResourceId(position: Int)=R.id.swipe

    inner class SlideViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvMid=itemView.findViewById<TextView>(R.id.tvMid)
        val tvDelete=itemView.findViewById<TextView>(R.id.tvDelete)
        val swipeLayout=itemView.findViewById<SwipeLayout>(R.id.swipe)
    }

    fun setDataList(dataList:ArrayList<String>){
        this.mList=dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        SlideViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slide2_item,parent,false))

    override fun getItemCount()=mList.size

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.tvMid.text=mList[position]
        holder.swipeLayout.showMode=SwipeLayout.ShowMode.LayDown
        holder.swipeLayout.addSwipeListener(object :SimpleSwipeListener(){

            override fun onOpen(layout: SwipeLayout?) {
                Log.i("lee","onOpen")
                Log.i("lee","$position")
                lastOpenPos=position
            }

            override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {
                super.onHandRelease(layout, xvel, yvel)
                Log.i("lee","onHandRelease")
            }

        })

        holder.tvDelete.setOnClickListener {
            mItemManger
            mList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }

    }




}