package com.technologies.zenlight.defaultapp.data.appLevel

import android.content.Context
import com.technologies.zenlight.defaultapp.utils.AppSharedPrefs

interface DataHelper {

    fun getAppContext() : Context

    fun getSharedPrefs(): AppSharedPrefs
}