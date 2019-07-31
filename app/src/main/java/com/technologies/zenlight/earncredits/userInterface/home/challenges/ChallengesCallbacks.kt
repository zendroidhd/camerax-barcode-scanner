package com.technologies.zenlight.earncredits.userInterface.home.challenges

import android.app.Activity
import com.technologies.zenlight.earncredits.data.model.api.Challenges

interface ChallengesCallbacks {

    fun handleError(title: String, body: String)

    fun onChallengesReturnedSuccessfully()

    fun showNoChallengesFoundPage()

    fun getActivityContext(): Activity?

    fun onAddNewChallengeClicked()

    fun onDeleteChallengeClicked(challenge: Challenges)

    fun requestsChallenges()

    fun onCompleteChallengeClicked(challenge: Challenges)
}