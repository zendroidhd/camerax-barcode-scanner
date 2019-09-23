package com.technologies.zenlight.defaultapp.userInterface.login.forgotPassword

import android.app.Activity

interface ForgotPasswordCallbacks {

    fun onExitButtonClicked()

    fun onResetButtonClicked()

    fun handleError(title: String, body: String)

    fun showEmailNotFoundAlert()

    fun showPasswordResetAlert()

    fun getActivityContext(): Activity?
}