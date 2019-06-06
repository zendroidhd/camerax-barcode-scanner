package com.technologies.zenlight.earncredits.data

import com.technologies.zenlight.earncredits.data.appLevel.AppDataHelper
import javax.inject.Inject

class AppDataManager @Inject constructor(private val appDataHelper: AppDataHelper) : DataManager {

    override fun getAppContext() = appDataHelper.getAppContext()
}