package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import android.app.Activity
import com.technologies.zenlight.earncredits.data.model.api.PowerUps

interface PowerUpsCallbacks {

    fun handleError(title: String, body: String)

    fun onPowerUpsReturnedSuccessfully()

    fun showNoPowerUpsFoundPage()

    fun getActivityContext(): Activity?

    fun onAddNewPowerUpClicked()

    fun onCompletePowerUpClicked(powerUps: PowerUps)

    fun onPowerUpSuccessfullyUsed(powerUps: PowerUps)

    fun onDeletePowerupClicked(powerUps: PowerUps)

    fun showNotEnoughCreditsAlert()

    fun requestPowerUps()
}