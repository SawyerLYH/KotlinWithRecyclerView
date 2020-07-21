package com.sawyer.kotlinrecyclerviewdemo.util

import android.content.Context
import android.content.Intent

//不传递参数
inline fun <reified T> startActivity(context: Context){
    val intent= Intent(context,T::class.java)
    context.startActivity(intent)
}

//可传递参数
inline fun <reified T> startActivity(context: Context,block:Intent.()->Unit){
    val intent=Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}