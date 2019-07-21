package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.Encouragements
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.utils.ENCOURAGEMENTS_COLLECTION
import com.technologies.zenlight.earncredits.utils.USERS_COLLECTION
import javax.inject.Inject

class HomeFragmentDataModel @Inject constructor(private val dataManager: AppDataManager) {


    fun getUserProfile(viewModel: HomeFragmentViewModel) {
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
                        viewModel.callbacks?.onUserProfileReturnedSuccessfully()

                    } else {
                        //should never happen at this point
                        viewModel.callbacks?.handleError("Error", "Error retrieving profile details")
                    }

                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

    fun getEncouragements(viewModel: HomeFragmentViewModel) {
        //todo get encouragements where seen = false
            val userId = dataManager.getSharedPrefs().userId
            val db = FirebaseFirestore.getInstance()
            db.collection(ENCOURAGEMENTS_COLLECTION)
                .whereEqualTo("recipientId", userId)
                .get()
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val encouragements = task.result!!.toObjects(Encouragements::class.java)


                    } else {
                        val message = task.exception?.message ?: "Authentication Failed (F)"
                        viewModel.callbacks?.handleError("Error", message)
                    }
                }
    }
}