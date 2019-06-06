package com.technologies.zenlight.earncredits.utils

import android.content.Context
import android.net.ConnectivityManager

fun isConnected(context: Context): Boolean {

    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    cm?.let {
        val activeNetwork = it.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
    return false
}