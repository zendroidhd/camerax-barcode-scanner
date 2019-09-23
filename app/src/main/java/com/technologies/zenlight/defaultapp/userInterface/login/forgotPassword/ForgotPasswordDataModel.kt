package com.technologies.zenlight.defaultapp.userInterface.login.forgotPassword

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.defaultapp.data.AppDataManager
import com.technologies.zenlight.defaultapp.data.model.api.UserProfile
import com.technologies.zenlight.defaultapp.utils.USERS_COLLECTION
import javax.inject.Inject

class ForgotPasswordDataModel @Inject constructor(private val dataManager: AppDataManager){


    fun submitUserCredentials(viewModel: ForgotPasswordViewModel, email: String) {

        val db = FirebaseFirestore.getInstance()
        db.collection(USERS_COLLECTION)
            .whereEqualTo("email", email)
            .get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val userProfiles = task.result!!.toObjects(UserProfile::class.java)
                    if (userProfiles.isNotEmpty()) {
                        //the email is legit send a firebase password reset
                        viewModel.userProfile = userProfiles[0]
                        sendPasswordResetEmail(viewModel, email)
                    } else {
                        //the entered email could not be found
                        viewModel.callbacks?.showEmailNotFoundAlert()
                    }

                } else {
                    val message = task.exception?.message ?: "Authentication Failed (F)"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

    private fun sendPasswordResetEmail(viewModel: ForgotPasswordViewModel, email: String) {
        val firebase = FirebaseAuth.getInstance()
        firebase.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    updateUserResetPasswordField(viewModel)

                } else {
                    val message = task.exception?.message ?: "Error sending password reset email"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

    /**
     * Updates the user's 'isResetPassword' field to true in the Firebase Db
     */
    private fun updateUserResetPasswordField(viewModel: ForgotPasswordViewModel) {

        val db = FirebaseFirestore.getInstance()
        val profile = viewModel.userProfile

        profile?.let {
            it.isPasswordReset = true
            db.collection(USERS_COLLECTION)
                .document(it.id)
                .set(profile)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        viewModel.callbacks?.showPasswordResetAlert()

                    } else {
                        val message = task.exception?.message ?: "Error sending password reset email"
                        viewModel.callbacks?.handleError("Error", message)
                    }
                }
        }

    }
}