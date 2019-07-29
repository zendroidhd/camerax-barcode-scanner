package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import android.annotation.SuppressLint
import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.userProfileObservable
import io.reactivex.Completable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel : BaseViewModel() {

    /******** Getters and Setters *********/

    var callbacks: HomeFragmentCallbacks? = null
    var dataModel: HomeFragmentDataModel? = null
    var userProfile: UserProfile? = null


    /******** OnClick Listeners **********/

    fun onHamburgerClicked() {
        callbacks?.onHamburgerClicked()
    }

    /******** Business Logic **********/

    private val profileObserver = object : Observer<UserProfile> {

        override fun onSubscribe(d: Disposable) { compositeDisposable.add(d) }

        @SuppressLint("CheckResult")
        override fun onNext(profile: UserProfile) {
            Completable.fromCallable { false }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    userProfile = profile
                    callbacks?.onUserProfileReturnedSuccessfully()
                }
        }
        override fun onError(e: Throwable) {}
        override fun onComplete() {}
    }

    fun setUpObservers() {
        userProfileObservable.subscribe(profileObserver)
    }

    /******* DataModel Requests *********/

//    fun getUserProfile() {
//        dataModel?.getUserProfile(this)
//    }
}