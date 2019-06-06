package com.technologies.zenlight.xrate.data

import android.content.Context
import com.technologies.zenlight.xrate.data.appLevel.AppDataHelper
import javax.inject.Inject

class AppDataManager @Inject constructor(private val appDataHelper: AppDataHelper) : DataManager {

    override fun getAppContext() = appDataHelper.getAppContext()
}