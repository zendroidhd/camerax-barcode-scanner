package com.technologies.zenlight.defaultapp.userInterface.login.signUp

import android.app.Activity

interface SignUpCallbacks {

    fun onStartNewGameClicked()

    fun onExitClicked()

    fun showEmailAlreadyInUseAlert()

    fun onUserAddedToFirebaseDatabase()

    fun signUserIntoApp()

    fun handleError(title: String, body: String)

    fun getActivityContext(): Activity?
}