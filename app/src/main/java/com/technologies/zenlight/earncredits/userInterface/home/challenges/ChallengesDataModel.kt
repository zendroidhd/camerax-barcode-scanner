package com.technologies.zenlight.earncredits.userInterface.home.challenges

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.HomeFragmentViewModel
import com.technologies.zenlight.earncredits.utils.CHALLENGES_COLLECTION
import com.technologies.zenlight.earncredits.utils.USERS_COLLECTION
import com.technologies.zenlight.earncredits.utils.pushUserProfileToObservers
import javax.inject.Inject

class ChallengesDataModel @Inject constructor(private val dataManager: AppDataManager) {


    fun getUserProfile(viewModel: ChallengesViewModel) {
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
                    val message = task.exception?.message ?: "Error retrieving the list of challenges"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }


    /**
     * This for when a user manually deletes a challenge. Their total credits is reduced by
     * the amount deleted
     */
    fun reduceCreditsForDeletedChallenge(viewModel: ChallengesViewModel, challenge: Challenges) {
        viewModel.userProfile?.let { profile ->
            val currentCredits = profile.credits
            var newCredits = currentCredits - challenge.credit
            if (newCredits < 0) {
                newCredits = 0
            }
            profile.credits = newCredits

            val db = FirebaseFirestore.getInstance()
            db.collection(USERS_COLLECTION)
                .document(profile.id)
                .set(profile)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        pushUserProfileToObservers(profile)
                        removeChallenge(viewModel, challenge)

                    } else {
                        val message = task.exception?.message ?: "Error updating profile"
                        viewModel.callbacks?.handleError("Error", message)
                    }
                }
        }
    }

     fun increaseCreditsForCompletedChallenge(viewModel: ChallengesViewModel, challenge: Challenges) {
        viewModel.userProfile?.let { profile ->
            val currentCredits = profile.credits
            val newCredits = currentCredits + challenge.credit

            profile.credits = newCredits

            val db = FirebaseFirestore.getInstance()
            db.collection(USERS_COLLECTION)
                .document(profile.id)
                .set(profile)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        pushUserProfileToObservers(profile)
                        removeChallenge(viewModel, challenge)

                    } else {
                        val message = task.exception?.message ?: "Error updating profile"
                        viewModel.callbacks?.handleError("Error", message)
                    }
                }
        }
    }


    /**
     * Removes the challenge from our Db
     */
     fun removeChallenge(viewModel: ChallengesViewModel, challenge: Challenges) {
        val db = FirebaseFirestore.getInstance()
        db.collection(CHALLENGES_COLLECTION)
            .document(challenge.id)
            .delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.challengesList.remove(challenge)
                    viewModel.callbacks?.onChallengesReturnedSuccessfully()
                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

}