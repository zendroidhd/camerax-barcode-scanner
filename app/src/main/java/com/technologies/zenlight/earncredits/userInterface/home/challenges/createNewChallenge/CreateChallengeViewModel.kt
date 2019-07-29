package com.technologies.zenlight.earncredits.userInterface.home.challenges.createNewChallenge

import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected

class CreateChallengeViewModel : BaseViewModel() {

    /********* Getters and Setters *********/

    var callbacks: CreateChallengeCallbacks? = null
    var dataModel: CreateChallengeDataModel? = null
    var completeByTimestamp: Long? = null

    /********** Business Logic ***********/

    fun isRequiredValuesFilled(description: String): Boolean {
        return description.isNotEmpty()
    }

    /********** OnClick Listeners *********/

    fun onCreateNewChallengeClicked() {
        callbacks?.onCreateNewChallengeClicked()
    }

    fun onSetDateClicked() {
        callbacks?.onSetDateClicked()
    }

    /******** DataModel Requests *********/

    fun createNewChallenge(challenge: Challenges) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.createNewChallenge(this,challenge)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }
}