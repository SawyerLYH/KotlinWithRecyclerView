package com.sawyer.kotlinrecyclerviewdemo.activity.group

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : BaseActivity() {

    val mList= mutableListOf<String>()
    val mDataList= mutableListOf<GroupBean>()

    override fun layoutRes()=R.layout.activity_group

    override fun initView() {
        groupRV.layoutManager=LinearLayoutManager(this)
        groupRV.adapter=GroupAdapter(mDataList)

    }

    override fun initData() {
        initList()
        for (beanStr in mList){
            val bean=GroupBean(beanStr.substring(0, beanStr.indexOf("|")),
                beanStr.substring(beanStr.indexOf("|") + 1))
            mDataList.add(bean)
        }
    }

    private fun initList() {
        mList.add("东南分区|亚特兰大老鹰")
        mList.add("东南分区|夏洛特黄蜂")
        mList.add("东南分区|迈阿密热火")
        mList.add("东南分区|奥兰多魔术")
        mList.add("东南分区|华盛顿奇才")
        mList.add("大西洋分区|波士顿凯尔特人")
        mList.add("大西洋分区|布鲁克林篮网")
        mList.add("大西洋分区|纽约尼克斯")
        mList.add("大西洋分区|费城76人")
        mList.add("大西洋分区|多伦多猛龙")
        mList.add("中央分区|芝加哥公牛")
        mList.add("中央分区|克里夫兰骑士")
        mList.add("中央分区|底特律活塞")
        mList.add("中央分区|印第安纳步行者")
        mList.add("中央分区|密尔沃基雄鹿")
        mList.add("西南分区|达拉斯独行侠")
        mList.add("西南分区|休斯顿火箭")
        mList.add("西南分区|孟菲斯灰熊")
        mList.add("西南分区|新奥尔良鹈鹕")
        mList.add("西南分区|圣安东尼奥马刺")
        mList.add("西北分区|丹佛掘金")
        mList.add("西北分区|明尼苏达森林狼")
        mList.add("西北分区|俄克拉荷马城雷霆")
        mList.add("西北分区|波特兰开拓者")
        mList.add("西北分区|犹他爵士")
        mList.add("太平洋分区|金州勇士")
        mList.add("太平洋分区|洛杉矶快船")
        mList.add("太平洋分区|洛杉矶湖人")
        mList.add("太平洋分区|菲尼克斯太阳")
        mList.add("太平洋分区|萨克拉门托国王")
    }
}
