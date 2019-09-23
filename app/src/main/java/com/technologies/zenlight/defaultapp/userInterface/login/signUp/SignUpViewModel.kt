package com.technologies.zenlight.defaultapp.userInterface.login.signUp

import com.technologies.zenlight.defaultapp.userInterface.base.BaseViewModel
import com.technologies.zenlight.defaultapp.utils.NO_NETWORK_BODY
import com.technologies.zenlight.defaultapp.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.defaultapp.utils.isConnected
import com.technologies.zenlight.defaultapp.utils.isEmailValid

class SignUpViewModel : BaseViewModel() {

    /*********** Getters and Setters ********/

    var dataModel: SignUpDataModel? = null
    var callbacks: SignUpCallbacks? = null

    /********* OnClick Listeners *********/

    fun onStartNewGameClicked () {
        callbacks?.onStartNewGameClicked()
    }

    fun onExitClicked() {
        callbacks?.onExitClicked()
    }

    /******* Business Logic ********/

    fun isCredentialsValid(email: String, password: String, reEnteredPassword: String): Boolean {
        return isEmailValid(email)
                && password.isNotEmpty()
                && reEnteredPassword.isNotEmpty()
                && password == reEnteredPassword
    }

    fun getInvalidCredentialsText(email: String, password: String, reEnteredPassword: String): String {
        return if (!isEmailValid(email) && password.isEmpty()) {
            "Please enter a valid email and password"
        } else if (!isEmailValid(email)) {
            "Please enter a valid email"
        } else if (password != reEnteredPassword) {
            "Your passwords do not match"
        } else {
            "Please enter a valid password"
        }
    }

    /********* DataModel Requests ********/

    fun submitLoginCredentials(email: String, password: String) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.submitLoginCredentials(this, email, password)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

    fun createFirebaseUser(email: String, password: String) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.createFirebaseUser(this,email,password)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

}