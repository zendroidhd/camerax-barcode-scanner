package com.technologies.zenlight.earncredits.userInterface.home.challenges

import android.annotation.SuppressLint
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected
import com.technologies.zenlight.earncredits.utils.userProfileObservable
import io.reactivex.Completable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ChallengesViewModel : BaseViewModel() {


    /******** Getters and Setters ********/

    var callbacks: ChallengesCallbacks? = null
    var dataModel: ChallengesDataModel? = null
    var challengesList = ArrayList<Challenges>()
    var userProfile: UserProfile? = null

    private val profileObserver = object : Observer<UserProfile> {

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        @SuppressLint("CheckResult")
        override fun onNext(profile: UserProfile) {
            Completable.fromCallable { false }.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    userProfile = profile
                }
        }

        override fun onError(e: Throwable) {}
        override fun onComplete() {}
    }

    /********* OnClick Listeners **********/

    fun onAddNewChallengeClicked() {
        callbacks?.onAddNewChallengeClicked()
    }

    /********* Business Logic *********/
    fun setUpObservers() {
        userProfileObservable.subscribe(profileObserver)
    }


    /******** DataModel Requests ********/

    fun getAllChallenges() {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.getUserProfile(this)
                dataModel?.getAllChallenges(this)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

    fun reduceCreditsForDeletedChallenge(challenge: Challenges) {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.reduceCreditsForDeletedChallenge(this, challenge)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

    fun completeChallenge(challenge: Challenges) {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.increaseCreditsForCompletedChallenge(this,challenge)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

}