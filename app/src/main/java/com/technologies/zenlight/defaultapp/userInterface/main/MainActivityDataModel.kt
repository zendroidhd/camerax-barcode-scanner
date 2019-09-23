package com.technologies.zenlight.defaultapp.userInterface.main

import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.defaultapp.data.AppDataManager
import com.technologies.zenlight.defaultapp.data.model.api.UserProfile
import com.technologies.zenlight.defaultapp.utils.USERS_COLLECTION
import javax.inject.Inject

class MainActivityDataModel @Inject constructor(private val dataManager: AppDataManager) {


    var viewModel: MainActivityViewModel? = null

        /**
         * Retrieves the User's profile
         */
        fun getUserProfile(viewModel: MainActivityViewModel) {
            val userId = dataManager.getSharedPrefs().userId
            val db = FirebaseFirestore.getInstance()
            this.viewModel = viewModel
            db.collection(USERS_COLLECTION)
                .whereEqualTo("id", userId)
                .get()
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val userProfiles = task.result!!.toObjects(UserProfile::class.java)
                        if (userProfiles.isNotEmpty()) {
                            viewModel.userProfile = userProfiles[0]
//                            pushUserProfileToObservers(userProfiles[0])
//
//                            //get nearby businesses
//                            getFirebaseNearbyBusinesses(viewModel)
//
//                            if (dataManager.getSharedPrefs().isLoggedInAsGuest) {
//                                returnRecentBusinesses(viewModel)
//                                returnFavoriteBusinesses(viewModel)
//
//                            } else {
//                                //get recent businesses
//                                setRecentlyViewedBusinesses(viewModel)
//                                //get favorite businesses
//                                setFavoriteBusinesses(viewModel)
//                            }


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
}