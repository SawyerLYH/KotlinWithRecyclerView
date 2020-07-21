package com.sawyer.kotlinrecyclerviewdemo.activity.loadmore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

sealed class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class NormalViewHolder(itemView: View) : MyViewHolder(itemView) {
    val tVItem=itemView.findViewById<TextView>(R.id.itemTv)
}

class FootViewHolder(itemView: View) : MyViewHolder(itemView) {
    val progressBar=itemView.findViewById<ProgressBar>(R.id.pb_loading)
    val tVLoading=itemView.findViewById<TextView>(R.id.tv_loading)
    val llEnd=itemView.findViewById<LinearLayout>(R.id.ll_end)
}


class LoadMoreAdapter(val context: Context, var list: ArrayList<String>) : RecyclerView.Adapter<MyViewHolder>() {

    var load_state:Int=2

    companion object {
        const val TYPE_ITEM = 1 //正常布局
        const val TYPE_FOOT = 2 //脚布局
    }

    //设置加载状态
    //1：加载中 2：加载完成 3：加载到底
    fun setLoadState(loadState: Int){
        load_state=loadState
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager=recyclerView.layoutManager
        if (layoutManager is GridLayoutManager){

            layoutManager.spanSizeLookup=object :GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position)== TYPE_FOOT) layoutManager.spanCount else 1
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        if (viewType == TYPE_ITEM) {
             NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.click_item, parent, false))
        } else{
             FootViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_refresh_footer, parent, false))
        }

    override fun getItemViewType(position: Int) = if (position + 1 == itemCount) TYPE_FOOT else TYPE_ITEM

    override fun getItemCount() = list.size + 1

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when(holder){
            is NormalViewHolder-> holder.tVItem.text=list[position]
            is FootViewHolder->
                if (load_state==1){//加载中
                    holder.progressBar.visibility= VISIBLE
                    holder.tVLoading.visibility= VISIBLE
                    holder.llEnd.visibility= GONE
                }else if (load_state==2){//加载完成
                    holder.progressBar.visibility= INVISIBLE
                    holder.tVLoading.visibility= INVISIBLE
                    holder.llEnd.visibility= GONE
                }else if(load_state==3){//加载到底
                    holder.progressBar.visibility= GONE
                    holder.tVLoading.visibility= GONE
                    holder.llEnd.visibility= VISIBLE
                }
        }
    }
}