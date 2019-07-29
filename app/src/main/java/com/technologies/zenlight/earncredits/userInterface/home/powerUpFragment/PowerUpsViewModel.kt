package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import android.annotation.SuppressLint
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
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

    /******** DataModel Requests **********/

    fun getAllPowerUps() {
        dataModel?.getAllPowerUps(this)
    }
}