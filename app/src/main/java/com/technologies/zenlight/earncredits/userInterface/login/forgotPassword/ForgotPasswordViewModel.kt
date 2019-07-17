package com.technologies.zenlight.earncredits.userInterface.login.forgotPassword

import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected

class ForgotPasswordViewModel : BaseViewModel() {


    /********** Getters and Setters **************/

    var callbacks: ForgotPasswordCallbacks? = null
    var dataModel: ForgotPasswordDataModel? = null
    var userProfile: UserProfile? = null

    /*********** OnClick Listeners ************/

    fun onExitButtonClicked() {
        callbacks?.onExitButtonClicked()
    }

    fun onResetButtonClicked() {
        callbacks?.onResetButtonClicked()
    }

    /********* DataModel Requests **********/

    fun submitUserCredentials(email: String) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.submitUserCredentials(this,email)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }
}