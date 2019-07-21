package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.utils.CHALLENGES_COLLECTION
import javax.inject.Inject

class ChallengesDataModel @Inject constructor(private val dataManager: AppDataManager) {


    fun getAllChallenges(viewModel: ChallengesViewModel) {

        val userId = dataManager.getSharedPrefs().userId
        val db = FirebaseFirestore.getInstance()
        viewModel.challengesList.clear()
        db.collection(CHALLENGES_COLLECTION)
            .whereEqualTo("authorId", userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val challenges = task.result!!.toObjects(Challenges::class.java)
                    if (challenges.isNotEmpty()) {
                        viewModel.challengesList.addAll(challenges)
                        viewModel.callbacks?.onChallengesReturnedSuccessfully()

                    } else {
                        viewModel.callbacks?.showNoChallengesFoundPage()
                    }

                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }
}