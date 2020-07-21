package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu3

import androidx.recyclerview.widget.LinearLayoutManager
import cn.we.swipe.helper.WeSwipe
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu.Slide3Adapter
import kotlinx.android.synthetic.main.activity_slide3.*

/**
 * 使用的为开源库：WeSwipe
 */
class Slide3Activity : BaseActivity() ,Slide3Adapter.onDelteItemClickListener{

    var mList=ArrayList<String>()
    var adatper= Slide3Adapter(this@Slide3Activity,this)

    override fun layoutRes()=R.layout.activity_slide3

    override fun initData() {
        for (i in 1..30){
            mList.add("item$i")
        }
        adatper.setDataList(mList,true)
    }

    override fun initView() {
        slide3RV.layoutManager= LinearLayoutManager(this)
        slide3RV.adapter=adatper
        //关联RecyclerView
        WeSwipe.attach(slide3RV)
    }

    override fun onDelteItemClick(position: Int) {
        adatper.removeDataByPosition(position)
    }

}
