package com.sawyer.kotlinrecyclerviewdemo.activity.swipe

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class SwipeItemTouchHelper(val mAdapter:SwipeAdapter) :ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //不允许上下滑动
        val dragFlags = 0
        //只允许从左向右滑动
        val swipeFlags = ItemTouchHelper.LEFT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder)=false

    //Item被选中时候回调
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder?.itemView?.setBackgroundResource(R.drawable.selected_bg)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    //移动过程中重新绘制 Item，随着滑动的距离，设置 Item 的透明度
    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val width=viewHolder.itemView.measuredWidth
        val x=Math.abs(dX)
        viewHolder.itemView.alpha=1f-x/width
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    //操作完毕后回复颜色
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.setBackgroundResource(R.drawable.common_bg)
//        viewHolder.itemView.alpha=1f
        super.clearView(recyclerView, viewHolder)
    }

    //不支持长按拖动
    override fun isLongPressDragEnabled()=false

    //支持滑动
    override fun isItemViewSwipeEnabled()=true

    //滑动删除 Item 的操作
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mAdapter.onItemDismiss(viewHolder.adapterPosition)
    }
}