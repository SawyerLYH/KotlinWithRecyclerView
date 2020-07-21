package com.sawyer.kotlinrecyclerviewdemo.activity.click

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_item_click.*

class ItemClickActivity : BaseActivity(),ClickAdapter.onMineItemClickListener{

    val list= mutableListOf<String>()

    override fun layoutRes()=R.layout.activity_item_click

    override fun initData() {
        for (i in 1..30){
            list.add("item $i")
        }
    }

    override fun initView() {
        val adapter=ClickAdapter(this,list,this)
        clickRV.layoutManager=LinearLayoutManager(this)
        clickRV.adapter=adapter
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this,"点击的是第${position+1}个条目",Toast.LENGTH_SHORT).show()
    }


}
