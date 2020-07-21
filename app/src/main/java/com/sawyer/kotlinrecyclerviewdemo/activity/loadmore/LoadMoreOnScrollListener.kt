package com.sawyer.kotlinrecyclerviewdemo.activity.loadmore

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class LoadMoreOnScrollListener:RecyclerView.OnScrollListener() {

    //用来标记是否正在向上滑动
    var isSlidingUpward=false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val manager= recyclerView.layoutManager as LinearLayoutManager
        //1.不滑动时，去判断
        if (newState==RecyclerView.SCROLL_STATE_IDLE){
            //2.获取最后一个完全显示的ItemPosition
            val lastPos=manager.findLastCompletelyVisibleItemPosition()
            val itemCount=manager.itemCount
            //3.判断是否滑动到了最后一个item，并且是向上滑动
            if (lastPos==itemCount-1 && isSlidingUpward){
                //4.加载更多
                onLoadMore()
            }
        }
    }

    //自己去实现加载更多的数据
    abstract fun onLoadMore()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward=dy>0
    }
}