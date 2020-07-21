package com.sawyer.kotlinrecyclerviewdemo.util

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.widget.Toast

object ToastUti {

    fun showCenterToast(text:String,context: Context){
        showCenterToast(text, context,0)
    }

    fun showCenterToast(text:String,context: Context,length:Int){
        if (TextUtils.isEmpty(text)) return
        val toast=Toast.makeText(context,text,length)
        toast.setMargin(0F,0.58F)
        toast.show()
    }

}