package com.sawyer.kotlinrecyclerviewdemo.activity.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_drag.*

class DragActivity : BaseActivity() {

    var mList=ArrayList<String>()

    override fun layoutRes()=R.layout.activity_drag

    override fun initView() {
        dragRV.layoutManager=LinearLayoutManager(this)
        val adatper=DragAdatper(this,mList)
        dragRV.adapter=adatper

        val callback=DragItemTouchHelper(adatper)
        val touchHelper=ItemTouchHelper(callback)
        //关联RecyclerView
        touchHelper.attachToRecyclerView(dragRV)

    }

    override fun initData() {
        mList.add("亚特兰大老鹰")
        mList.add("夏洛特黄蜂")
        mList.add("迈阿密热火")
        mList.add("奥兰多魔术")
        mList.add("华盛顿奇才")
        mList.add("波士顿凯尔特人")
        mList.add("布鲁克林篮网")
        mList.add("纽约尼克斯")
        mList.add("费城76人")
        mList.add("多伦多猛龙")
        mList.add("芝加哥公牛")
        mList.add("克里夫兰骑士")
        mList.add("底特律活塞")
        mList.add("印第安纳步行者")
        mList.add("密尔沃基雄鹿")
        mList.add("达拉斯独行侠")
        mList.add("休斯顿火箭")
        mList.add("孟菲斯灰熊")
        mList.add("新奥尔良鹈鹕")
        mList.add("圣安东尼奥马刺")
    }
}
