package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.createNewPowerup

import android.app.Activity

interface CreatePowerupCallbacks {

    fun handleError(title: String, body: String)

    fun getActivityContext(): Activity?

    fun onPowerupCreatedSuccessfully()

    fun onCreateNewPowerupClicked()
}