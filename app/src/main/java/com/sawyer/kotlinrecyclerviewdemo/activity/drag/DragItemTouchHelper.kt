package com.sawyer.kotlinrecyclerviewdemo.activity.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.R

class DragItemTouchHelper(val mAdatper: DragAdatper) : ItemTouchHelper.Callback() {


    /**
     * 设置滑动类型标记
     * @param recyclerView
     * @param viewHolder
     * @return 返回一个整数类型的标识，用于判断Item哪种移动行为是允许的
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //允许上下滑动
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        //不允许左右滑动
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    /**
     * 拖拽切换 Item 的回调
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return true-> Item切换了位置，false-> Item没切换位置
     */
    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mAdatper.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    /**
     * Item被选中时候回调
     *
     * @param viewHolder
     * @param actionState 当前Item的状态
     *                    ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
     *                    ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
     *                    ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        //item被选中的操作
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder?.itemView?.setBackgroundResource(R.drawable.selected_bg)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        //操作完毕后回复颜色
        viewHolder.itemView.setBackgroundResource(R.drawable.common_bg)
        super.clearView(recyclerView, viewHolder)
    }

    //支持长按拖动
    override fun isLongPressDragEnabled()=true

    //不支持滑动
    override fun isItemViewSwipeEnabled()=false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}