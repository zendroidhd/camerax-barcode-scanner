package com.technologies.zenlight.earncredits.userInterface.home.challenges.createNewChallenge

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.utils.CHALLENGES_COLLECTION
import javax.inject.Inject

class CreateChallengeDataModel @Inject constructor(private val dataManager: AppDataManager) {


    fun createNewChallenge(viewModel: CreateChallengeViewModel, challenge: Challenges) {

        val userId = dataManager.getSharedPrefs().userId ?: ""
        val db = FirebaseFirestore.getInstance()
        val newChallengeRef = db.collection(CHALLENGES_COLLECTION).document()
        val newChallengeId: String = newChallengeRef.id
        challenge.id = newChallengeId
        challenge.authorId = userId

        newChallengeRef.set(challenge)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.callbacks?.onChallengeCreatedSuccessfully()

                } else {
                    viewModel.callbacks?.handleError("Error","Authentication Failed")
                }
            }
    }
}