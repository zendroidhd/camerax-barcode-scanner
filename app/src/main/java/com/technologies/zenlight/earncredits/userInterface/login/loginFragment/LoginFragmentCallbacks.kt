package com.technologies.zenlight.earncredits.userInterface.login.loginFragment

import android.app.Activity

interface LoginFragmentCallbacks {

    fun onForgotPasswordClicked()

    fun onSignUpClicked()

    fun onEnterButtonClicked()

    fun handleError(title: String, body: String)

    fun showSignUpAlert()

    fun showForgotPasswordAlert()

    fun onCredentialsReturnedSuccessfully()

    fun signUserIntoApp()

    fun getActivityContext(): Activity?

}