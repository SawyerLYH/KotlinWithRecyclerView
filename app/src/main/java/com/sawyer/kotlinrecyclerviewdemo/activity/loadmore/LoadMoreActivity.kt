package com.sawyer.kotlinrecyclerviewdemo.activity.loadmore

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_load_more.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class LoadMoreActivity : BaseActivity() {

    var mList=ArrayList<String>()
    val adatper=LoadMoreAdapter(this,mList)

    override fun layoutRes()=R.layout.activity_load_more

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolBar)
    }

    override fun initView() {
        swipeRefresh.setColorSchemeColors(Color.parseColor("#4DB6AC"),Color.parseColor("#FF5722"))
        loadMoreRV.layoutManager=LinearLayoutManager(this)
        loadMoreRV.adapter=adatper

        swipeRefresh.setOnRefreshListener {
            //刷新数据
            mList.clear()
//            initData()
            refreshData()
            adatper.notifyDataSetChanged()
            swipeRefresh.postDelayed({
                if (swipeRefresh!=null&&swipeRefresh.isRefreshing)
                    swipeRefresh.isRefreshing=false
            },1000)
        }

        loadMoreRV.addOnScrollListener(object:LoadMoreOnScrollListener(){
            override fun onLoadMore() {
                //触发加载更多，更新状态为加载中
                adatper.setLoadState(1)
                if (mList.size<78){
                    //模拟获取网络数据，延时1s
                    Timer().schedule(1000){
                        runOnUiThread{
                            initData()
                            adatper.setLoadState(2)
                        }
                    }
                }else{
                    //没有更多的数据，更新状态为加载到底
                    adatper.setLoadState(3)
                }
            }
        })
    }

    private fun refreshData(){
        for (i in 1..30){
            mList.add("item $i")
        }
    }

    override fun initData() {
        var letter='A'
        for (i in 1..26){
            mList.add(letter.toString())
            letter++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.load_more_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            R.id.linear==item.itemId->
                loadMoreRV.layoutManager=LinearLayoutManager(this)
            R.id.grid==item.itemId->
                loadMoreRV.layoutManager=GridLayoutManager(this,2)
        }

        mList.clear()
        initData()
        loadMoreRV.adapter=adatper

        return super.onOptionsItemSelected(item)
    }

}
