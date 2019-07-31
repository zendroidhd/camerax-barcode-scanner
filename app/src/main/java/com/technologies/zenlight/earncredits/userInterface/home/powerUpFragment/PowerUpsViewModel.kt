package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import android.annotation.SuppressLint
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected
import com.technologies.zenlight.earncredits.utils.userProfileObservable
import io.reactivex.Completable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PowerUpsViewModel : BaseViewModel() {

    /********** Getters and Setters *********/

    var callbacks: PowerUpsCallbacks? = null
    var dataModel: PowerUpsDataModel? = null
    var userProfile: UserProfile? = null
    var powerUpsList = ArrayList<PowerUps>()


    /******** Business Logic ******/

    private val profileObserver = object : Observer<UserProfile> {

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        @SuppressLint("CheckResult")
        override fun onNext(profile: UserProfile) {
            Completable.fromCallable { false }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    userProfile = profile
                }
        }

        override fun onError(e: Throwable) {}
        override fun onComplete() {}
    }

    fun setUpObservers() {
        userProfileObservable.subscribe(profileObserver)
    }

    /********* OnClick Listeners ********/

    fun onAddNewPowerUpClicked() {
        callbacks?.onAddNewPowerUpClicked()
    }

    /******** DataModel Requests **********/

    fun getAllPowerUps() {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.getAllPowerUps(this)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

    fun decreaseCreditsForUsedPowerup(powerUps: PowerUps) {
        callbacks?.getActivityContext()?.let { activity ->
            if (isConnected(activity)) {
                dataModel?.decreaseCreditsForUsedPowerup(this, powerUps)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }

        fun removePowerup(powerUps: PowerUps) {
            callbacks?.getActivityContext()?.let { activity ->
                if (isConnected(activity)) {
                    dataModel?.removePowerup(this, powerUps)
                } else {
                    callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
                }
            }
        }
}