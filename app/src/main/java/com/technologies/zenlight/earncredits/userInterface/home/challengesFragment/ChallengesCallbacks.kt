package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment

import android.app.Activity

interface ChallengesCallbacks {

    fun handleError(title: String, body: String)

    fun onChallengesReturnedSuccessfully()

    fun showNoChallengesFoundPage()

    fun getActivityContext(): Activity?

    fun onAddNewChallengeClicked()

    fun requestsChallenges()
}