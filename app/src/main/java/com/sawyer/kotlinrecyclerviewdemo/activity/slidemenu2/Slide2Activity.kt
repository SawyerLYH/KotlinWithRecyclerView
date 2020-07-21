package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu2

import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_slide2.*
import java.util.jar.Attributes

class Slide2Activity : BaseActivity() {
    var mList=ArrayList<String>()
    var adatper= Slide2Adapter()

    override fun layoutRes()=R.layout.activity_slide2

    override fun initData() {
        for (i in 1..30){
            mList.add("item$i")
        }
    }

    override fun initView() {
        slide2RV.layoutManager= LinearLayoutManager(this)
//        adatper.mode=com.daimajia.swipe.util.Attributes.Mode.Single
        slide2RV.adapter=adatper
        adatper.setDataList(mList)

    }
}
