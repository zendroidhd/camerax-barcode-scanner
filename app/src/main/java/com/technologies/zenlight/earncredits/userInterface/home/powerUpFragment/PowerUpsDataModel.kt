package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.home.challenges.ChallengesViewModel
import com.technologies.zenlight.earncredits.utils.POWERUPS_COLLECTION
import com.technologies.zenlight.earncredits.utils.USERS_COLLECTION
import com.technologies.zenlight.earncredits.utils.pushUserProfileToObservers
import javax.inject.Inject

class PowerUpsDataModel @Inject constructor(private val dataManager: AppDataManager) {



    fun getUserProfile(viewModel: PowerUpsViewModel) {
        val userId = dataManager.getSharedPrefs().userId
        val db = FirebaseFirestore.getInstance()
        db.collection(USERS_COLLECTION)
            .whereEqualTo("id", userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userProfiles = task.result!!.toObjects(UserProfile::class.java)
                    if (userProfiles.isNotEmpty()) {
                        viewModel.userProfile = userProfiles[0]
                        pushUserProfileToObservers(userProfiles[0])
                    }
                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }


    fun getAllPowerUps(viewModel: PowerUpsViewModel) {
        val userId = dataManager.getSharedPrefs().userId
        val db = FirebaseFirestore.getInstance()
        viewModel.powerUpsList.clear()
        db.collection(POWERUPS_COLLECTION)
            .whereEqualTo("authorId", userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val powerUps = task.result!!.toObjects(PowerUps::class.java)
                    if (powerUps.isNotEmpty()) {
                        viewModel.powerUpsList.addAll(powerUps)
                        viewModel.callbacks?.onPowerUpsReturnedSuccessfully()

                    } else {
                        viewModel.callbacks?.showNoPowerUpsFoundPage()
                    }

                } else {
                    val message = task.exception?.message ?: "Error retrieving the list of challenges"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

    /**
     * Removes the challenge from our Db
     */
    private fun removePowerup(viewModel: PowerUpsViewModel, powerUps: PowerUps) {
        val db = FirebaseFirestore.getInstance()
        db.collection(POWERUPS_COLLECTION)
            .document(powerUps.id)
            .delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.powerUpsList.remove(powerUps)
                    if (viewModel.powerUpsList.isEmpty()) {
                        viewModel.callbacks?.showNoPowerUpsFoundPage()

                    } else {
                        viewModel.callbacks?.onPowerUpsReturnedSuccessfully()
                    }
                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }
}