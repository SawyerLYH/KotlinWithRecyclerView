package com.sawyer.kotlinrecyclerviewdemo.activity.drag

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition:Int,goPosition:Int)
}