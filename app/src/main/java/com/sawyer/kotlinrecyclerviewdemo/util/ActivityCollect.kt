package com.sawyer.kotlinrecyclerviewdemo.util

import android.app.Activity

//收集Activity工具类
object ActivityCollect {

    var activitys = ArrayList<Activity>()

    fun addActivity(act: Activity) {
        activitys.add(act)
    }

    fun removeActivity(act: Activity) {
        if (!act.isFinishing) activitys.remove(act)
    }

    //在任何想要退出程序的地方调用
    fun exitApp(){
        if (activitys.size>0){
            for (act in activitys){
                act.finish()
            }
        }
    }

}