package com.sawyer.kotlinrecyclerviewdemo.util

import android.util.Log

object LogUtil {

    const val VERBOSE=1
    const val DEBUG=2
    const val INFO=3
    const val WARN=4
    const val ERROR=5
    const val NOTHING=6
    const val TAG="lee"

    val level= VERBOSE

    fun v(msg:String){if (level<= VERBOSE) Log.v(TAG,msg)}

    fun d(msg:String){if (level<= DEBUG) Log.d(TAG,msg)}

    fun i(msg:String){if (level<= INFO) Log.i(TAG,msg)}

    fun w(msg:String){if (level<= WARN) Log.w(TAG,msg)}

    fun e(msg:String){if (level<= ERROR) Log.e(TAG,msg)}


}