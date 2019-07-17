package com.technologies.zenlight.earncredits.data.appLevel

import android.content.Context
import com.technologies.zenlight.earncredits.utils.AppSharedPrefs

interface DataHelper {

    fun getAppContext() : Context

    fun getSharedPrefs(): AppSharedPrefs
}