package com.technologies.zenlight.defaultapp.userInterface.login.loginFragment

import android.app.Activity

interface LoginFragmentCallbacks {

    fun onForgotPasswordClicked()

    fun onSignUpClicked()

    fun onSignInButtonClicked()

    fun handleError(title: String, body: String)

    fun showSignUpAlert()

    fun showForgotPasswordAlert()

    fun onCredentialsReturnedSuccessfully()

    fun signUserIntoApp()

    fun getActivityContext(): Activity?

}