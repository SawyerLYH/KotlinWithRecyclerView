package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.we.swipe.helper.WeSwipeHelper
import com.sawyer.kotlinrecyclerviewdemo.R


class Slide3Adapter(var context:Context,var listener: onDelteItemClickListener) : RecyclerView.Adapter<Slide3Adapter.SlideViewHolder>() {

    var mList=ArrayList<String>()

    inner class SlideViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), WeSwipeHelper.SwipeLayoutTypeCallBack{

        val tvDel=itemView.findViewById<TextView>(R.id.item_slide)
        val tvItem=itemView.findViewById<TextView>(R.id.item_text)

        //侧滑菜单的宽度，也是侧滑的距离
        override fun getSwipeWidth(): Float {
            return tvDel.width.toFloat()
        }
        //未滑动之前展现在屏幕中的布局
        override fun onScreenView(): View {
            return tvItem
        }
        //需要发生滑动的布局
        override fun needSwipeLayout(): View {
            return tvItem
        }
    }

    fun setDataList(dataList:ArrayList<String>,refresh:Boolean){
        if (refresh){
            mList.clear()
        }
        mList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SlideViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.slide3_item,parent,false)
        return SlideViewHolder(view)
    }

    override fun getItemCount()=mList.size

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.tvItem.text=mList[position]

        holder.tvItem.setOnClickListener {
            Toast.makeText(context,"点击了条目$position",Toast.LENGTH_SHORT).show()
        }

        holder.tvDel.setOnClickListener {
            listener.onDelteItemClick(holder.adapterPosition)
        }

    }

    fun removeDataByPosition(position: Int) {
        if (position >= 0 && position < mList.size) {
            mList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mList.size - 1)
        }
    }

    interface onDelteItemClickListener{
        fun onDelteItemClick(position:Int)
    }

}



