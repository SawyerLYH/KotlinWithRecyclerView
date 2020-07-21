package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu

import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_slide.*

class SlideActivity : BaseActivity() {


    var mList=ArrayList<String>()
    var adatper=SlideAdapter()

    override fun layoutRes()=R.layout.activity_slide

    override fun initData() {
        for (i in 1..30){
            mList.add("item$i")
        }
    }

    override fun initView() {
        slideRV.layoutManager=LinearLayoutManager(this)
        slideRV.adapter=adatper
        adatper.setDataList(mList)
    }

}
