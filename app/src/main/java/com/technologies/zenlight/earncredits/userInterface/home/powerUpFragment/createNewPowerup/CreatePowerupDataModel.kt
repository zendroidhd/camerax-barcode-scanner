package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.createNewPowerup

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.utils.POWERUPS_COLLECTION
import javax.inject.Inject

class CreatePowerupDataModel @Inject constructor(private val dataManager: AppDataManager) {

    fun createNewPowerup(viewModel: CreatePowerupViewModel, powerUp: PowerUps) {
        val userId = dataManager.getSharedPrefs().userId ?: ""
        val db = FirebaseFirestore.getInstance()
        val newPowerUpRef = db.collection(POWERUPS_COLLECTION).document()
        val newPowerUpId: String = newPowerUpRef.id
        powerUp.id = newPowerUpId
        powerUp.authorId = userId

        newPowerUpRef.set(powerUp)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.callbacks?.onPowerupCreatedSuccessfully()

                } else {
                    viewModel.callbacks?.handleError("Error","Authentication Failed")
                }
            }
    }
}