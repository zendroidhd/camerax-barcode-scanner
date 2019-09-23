package com.technologies.zenlight.defaultapp.userInterface.login.loginFragment

import com.technologies.zenlight.defaultapp.data.model.api.UserProfile
import com.technologies.zenlight.defaultapp.userInterface.base.BaseViewModel
import com.technologies.zenlight.defaultapp.utils.NO_NETWORK_BODY
import com.technologies.zenlight.defaultapp.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.defaultapp.utils.isConnected
import com.technologies.zenlight.defaultapp.utils.isEmailValid

class LoginFragmentViewModel : BaseViewModel() {

    /********* Getters and Setters **********/

    var callbacks : LoginFragmentCallbacks? = null
    var dataModel: LoginFragmentDataModel? = null
    var userProfile: UserProfile? = null


    /********* OnClick Listeners *************/

    fun onForgotPasswordClicked() {
        callbacks?.onForgotPasswordClicked()
    }

    fun onSignUpClicked() {
        callbacks?.onSignUpClicked()
    }

    fun onSignInButtonClicked() {
        callbacks?.onSignInButtonClicked()
    }

    /*********** Business Logic **********/

    fun isLoginValuesValid(email: String, code: String): Boolean {
        return isEmailValid(email) && code.isNotEmpty()
    }

    fun getEmptyLoginValuesText(email: String, code: String): String {
        return if (!isEmailValid(email) && code.isEmpty()) {
            "Please enter a valid email and password"
        } else if (!isEmailValid(email)) {
            "Please enter a valid email"
        } else {
            "Please enter a valid password"
        }
    }


    /******* DataModel Requests *********/

    fun submitLoginCredentials(email: String, password: String) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.submitLoginCredentials(this,email,password)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

    fun signUserIntoFirebase(email: String,password: String) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.signUserIntoFirebase(this,email,password)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }
}