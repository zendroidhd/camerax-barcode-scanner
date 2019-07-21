package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment.createNewChallenge

import android.app.Activity

interface CreateChallengeCallbacks {

    fun handleError(title: String, body: String)

    fun onChallengeCreatedSuccessfully()

    fun onCreateNewChallengeClicked()

    fun onSetDateClicked()

    fun getActivityContext(): Activity?

}