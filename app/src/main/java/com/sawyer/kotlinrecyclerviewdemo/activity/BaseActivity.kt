package com.sawyer.kotlinrecyclerviewdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sawyer.kotlinrecyclerviewdemo.util.ActivityCollect

abstract class BaseActivity:AppCompatActivity() {

    open fun layoutRes()=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        ActivityCollect.addActivity(this)
        initData()
        initView()
    }

    abstract fun initData()

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollect.removeActivity(this)
    }


}