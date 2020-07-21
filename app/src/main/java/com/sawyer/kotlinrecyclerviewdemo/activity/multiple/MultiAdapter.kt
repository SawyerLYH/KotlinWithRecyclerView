package com.sawyer.kotlinrecyclerviewdemo.activity.multiple

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class MultiAdapter(var listener:onMineItemClickListener):RecyclerView.Adapter<MultiAdapter.MultiViewHolder>() {

    companion object{
        const val MODE_NORMAL=0
    }

    var mEditMode= MODE_NORMAL
    var mList=ArrayList<MultiBean>()

    inner class MultiViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgCheckBox=itemView.findViewById<ImageView>(R.id.check_box)
        val tvItem=itemView.findViewById<TextView>(R.id.tv_multi_item)
    }

    fun notifyAdapter(dataList:ArrayList<MultiBean>,isAdd:Boolean){
        if (!isAdd){
            this.mList=dataList
        }else{
            this.mList.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    fun setEditMode(editMode:Int){
        this.mEditMode=editMode
        notifyDataSetChanged()
    }

    fun getDataList()=mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MultiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.multi_item,parent,false))

    override fun getItemCount(): Int =mList.size

    override fun onBindViewHolder(holder: MultiViewHolder, position: Int) {
        val multiBean=mList[holder.adapterPosition]
        holder.tvItem.text=multiBean.title
        if (mEditMode== MODE_NORMAL){
            holder.imgCheckBox.visibility=GONE
        }else{
            holder.imgCheckBox.visibility= VISIBLE
        }
        //根据条目里面的是否选中属性判断checkbox的状态
        if (multiBean.isSelect){
            holder.imgCheckBox.setImageResource(R.drawable.ic_checked)
        }else{
            holder.imgCheckBox.setImageResource(R.drawable.ic_uncheck)
        }

        holder.itemView.setOnClickListener {
            listener.onMineItemClick(position,mList)
        }

    }

    interface onMineItemClickListener{
        fun onMineItemClick(position: Int,list: ArrayList<MultiBean>)
    }



}