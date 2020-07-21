package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu4

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import com.yanzhenjie.recyclerview.*
import kotlinx.android.synthetic.main.activity_slide4.*

/**
 * 使用的开源库：SwipeRecyclerView
 */
class Slide4Activity : BaseActivity() {

    var mList=ArrayList<String>()
    val adapter=Slide4Adapter(this)

    override fun layoutRes()=R.layout.activity_slide4

    override fun initData() {
        for (i in 1..30){
            mList.add("item$i")
        }
        adapter.setDataList(mList,false)
    }

    override fun initView() {
        slide4RV.setSwipeMenuCreator(swipeMenuCreator)
        slide4RV.setOnItemMenuClickListener(onItemMenuClickListener)
        slide4RV.layoutManager=LinearLayoutManager(this)
        slide4RV.adapter=adapter
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private val swipeMenuCreator= object :SwipeMenuCreator{
        override fun onCreateMenu(leftMenu: SwipeMenu?, rightMenu: SwipeMenu?, position: Int) {
            val appointWidth=resources.getDimensionPixelSize(R.dimen.dp_88)
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            val appointHeight=ViewGroup.LayoutParams.MATCH_PARENT

            // 添加左侧的，如果不添加，则左侧不会出现菜单
            {}

            // 添加右侧的，如果不添加，则右侧不会出现菜单
            val topItem:SwipeMenuItem=SwipeMenuItem(this@Slide4Activity).apply {
                setBackground(R.drawable.selector_red)
                text="置顶"
                setTextColor(Color.WHITE)
                width=appointWidth
                height=appointHeight
            }
            rightMenu?.addMenuItem(topItem)

            val delItem:SwipeMenuItem=SwipeMenuItem(this@Slide4Activity).apply {
                setBackground(R.drawable.selector_green)
                text="删除"
                setTextColor(Color.WHITE)
                width=appointWidth
                height=appointHeight
            }
            rightMenu?.addMenuItem(delItem)

        }
    }

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private val onItemMenuClickListener =object:OnItemMenuClickListener{
        override fun onItemClick(menuBridge: SwipeMenuBridge?, adapterPosition: Int) {
            menuBridge?.closeMenu()
            //区分左右侧菜单
            val direction = menuBridge?.direction

            //菜单在RecyclerView的Item中的Position
            val meunPosition = menuBridge?.position

            if (direction==SwipeRecyclerView.RIGHT_DIRECTION){
                if (meunPosition==0){//置顶操作
                    val topStr=mList[adapterPosition]
                    mList.removeAt(adapterPosition)
                    mList.add(0,topStr)
                    adapter.setDataList(mList,true)
                    adapter.notifyItemInserted(0)

                }else if (meunPosition==1){//删除操作
                    mList.removeAt(adapterPosition)
                    adapter.setDataList(mList,true)
                    adapter.notifyItemRemoved(adapterPosition)
                    adapter.notifyItemRangeChanged(adapterPosition,mList.size-1)
                }
            }

        }
    }



}
