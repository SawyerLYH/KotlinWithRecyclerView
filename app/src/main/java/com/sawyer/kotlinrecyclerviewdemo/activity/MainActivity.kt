package com.sawyer.kotlinrecyclerviewdemo.activity

import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.click.ItemClickActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.drag.DragActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.group.GroupActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.loadmore.LoadMoreActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.multiple.MultiActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu.SlideActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu2.Slide2Activity
import com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu3.Slide3Activity
import com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu4.Slide4Activity
import com.sawyer.kotlinrecyclerviewdemo.activity.sticky.StickyActivity
import com.sawyer.kotlinrecyclerviewdemo.activity.swipe.SwipeActivity
import com.sawyer.kotlinrecyclerviewdemo.util.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initData() {}

    override fun initView() {
        initListener()
    }

    override fun layoutRes() = R.layout.activity_main

    private fun initListener() {
        btn1.setOnClickListener { startActivity<ItemClickActivity>(this) }

        btn2.setOnClickListener { startActivity<GroupActivity>(this) }

        btn3.setOnClickListener { startActivity<StickyActivity>(this) }

        btn4.setOnClickListener { startActivity<DragActivity>(this) }

        btn5.setOnClickListener { startActivity<SwipeActivity>(this) }

        btn6.setOnClickListener { startActivity<LoadMoreActivity>(this) }

        btn7.setOnClickListener { startActivity<MultiActivity>(this) }

        btn8.setOnClickListener { startActivity<SlideActivity>(this) }

        btn9.setOnClickListener { startActivity<Slide2Activity>(this) }

        btn10.setOnClickListener { startActivity<Slide3Activity>(this) }

        btn11.setOnClickListener { startActivity<Slide4Activity>(this) }

    }

}
