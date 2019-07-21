package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment

import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected
import okhttp3.Challenge

class ChallengesViewModel : BaseViewModel() {

    /******** Getters and Setters ********/

    var callbacks: ChallengesCallbacks? = null
    var dataModel: ChallengesDataModel? = null
    var challengesList = ArrayList<Challenges>()

    /********* OnClick Listeners **********/

    fun onAddNewChallengeClicked() {
        callbacks?.onAddNewChallengeClicked()
    }

    /******** DataModel Requests ********/

    fun getAllChallenges() {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.getAllChallenges(this)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

}