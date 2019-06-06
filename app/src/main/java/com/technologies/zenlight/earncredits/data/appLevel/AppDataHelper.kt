package com.technologies.zenlight.earncredits.data.appLevel

import android.content.Context
import javax.inject.Inject

class AppDataHelper @Inject constructor(private val appContext : Context) : DataHelper {

    override fun getAppContext() = appContext
}