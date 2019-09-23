package com.technologies.zenlight.defaultapp.userInterface.login.loginFragment

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.technologies.zenlight.defaultapp.data.AppDataManager
import com.technologies.zenlight.defaultapp.data.model.api.UserProfile
import com.technologies.zenlight.defaultapp.utils.USERS_COLLECTION

import javax.inject.Inject

class LoginFragmentDataModel @Inject constructor(private val dataManager: AppDataManager){

    /**
     * Checks the user's email and password in our database
     */
    fun submitLoginCredentials(viewModel: LoginFragmentViewModel, email:String, password:String) {
        val db = FirebaseFirestore.getInstance()
        val profiles: ArrayList<UserProfile> = ArrayList()
        db.collection(USERS_COLLECTION)
            .whereEqualTo("email", email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userProfiles = task.result!!.toObjects(UserProfile::class.java)
                    profiles.addAll(userProfiles)
                    checkUserProfile(viewModel,profiles,password)

                } else {
                    val message = task.exception?.message?: "Please try again"
                    viewModel.callbacks?.handleError("Error", message)
                }
            }
    }

    private fun checkUserProfile(viewModel: LoginFragmentViewModel, profiles: List<UserProfile>, password: String) {

        if (profiles.isEmpty()) {
            //this user has never signed up, prompt to sign up
            viewModel.callbacks?.showSignUpAlert()

        } else {
            val profile = profiles[0]
            viewModel.userProfile = profile

            if (profile.isPasswordReset) {
                signUserIntoFirebase(viewModel,profile.email,password)

            } else {
                if (profile.password == password) {
                    //credentials are correct
                    dataManager.getSharedPrefs().userId = profile.id
                    viewModel.callbacks?.onCredentialsReturnedSuccessfully()
                } else {
                    //email is right but password is incorrect
                    viewModel.callbacks?.showForgotPasswordAlert()
                }
            }
        }
    }

    fun signUserIntoFirebase(viewModel: LoginFragmentViewModel, email: String, password:String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val profile = viewModel.userProfile
                    profile?.let {
                        if (it.isPasswordReset) {
                            updateUserProfile(viewModel,password)
                        } else {
                            viewModel.callbacks?.signUserIntoApp()
                        }
                    }

                } else {
                    val message = task.exception?.message ?: "Authentication Failed"
                    viewModel.callbacks?.handleError("Error",message)
                }
            }
    }

    /**
     * Updates the user's 'isResetPassword' and 'Password' fields to true in the Firebase Db
     */
    private fun updateUserProfile(viewModel: LoginFragmentViewModel, password: String) {

        val db = FirebaseFirestore.getInstance()
        val profile = viewModel.userProfile

        profile?.let {
            it.isPasswordReset = false
            it.password = password

            db.collection(USERS_COLLECTION)
                .document(it.id)
                .set(profile)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModel.callbacks?.signUserIntoApp()
                    } else {
                        val message = task.exception?.message ?: "Error reseting password"
                        viewModel.callbacks?.handleError("Error", message)
                    }
                }
        }
    }
}