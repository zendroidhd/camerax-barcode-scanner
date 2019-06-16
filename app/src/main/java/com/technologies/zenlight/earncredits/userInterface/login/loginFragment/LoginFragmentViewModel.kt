package com.technologies.zenlight.earncredits.userInterface.login.loginFragment

import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel

class LoginFragmentViewModel : BaseViewModel() {

    /********* Getters and Setters **********/

    var callbacks : LoginFragmentCallbacks? = null


    /********* OnClick Listeners *************/

    fun onForgotPasswordClicked() {
        callbacks?.onForgotPasswordClicked()
    }

    fun onSignUpClicked() {
        callbacks?.onSignUpClicked()
    }

    fun onEnterButtonClicked() {
        callbacks?.onEnterButtonClicked()
    }
}