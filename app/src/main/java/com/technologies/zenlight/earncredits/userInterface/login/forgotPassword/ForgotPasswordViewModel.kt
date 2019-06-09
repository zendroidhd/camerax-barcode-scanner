package com.technologies.zenlight.earncredits.userInterface.login.forgotPassword

import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel

class ForgotPasswordViewModel : BaseViewModel() {


    /********** Getters and Setters **************/

    var callbacks: ForgotPasswordCallbacks? = null

    var dataModel: ForgotPasswordDataModel? = null

    /*********** OnClick Listeners ************/

    fun onExitButtonClicked() {
        callbacks?.onExitButtonClicked()
    }
}