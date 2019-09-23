package com.technologies.zenlight.defaultapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import java.util.*


var isUnitTesting = false


fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = cm.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun isEmailValid(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

val dateFormatter = java.text.SimpleDateFormat("MM/dd/yyyy", Locale.US)

val REMOVE_NON_DIGITS = Regex("[^\\d]")
