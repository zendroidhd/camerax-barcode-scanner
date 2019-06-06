package com.technologies.zenlight.xrate.data.appLevel

import android.content.Context
import javax.inject.Inject

class AppDataHelper @Inject constructor(private val appContext : Context) : DataHelper {

    override fun getAppContext() = appContext
}