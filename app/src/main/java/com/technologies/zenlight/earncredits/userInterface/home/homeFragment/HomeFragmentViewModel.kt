package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import com.technologies.zenlight.earncredits.data.model.api.UserProfile
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel

class HomeFragmentViewModel : BaseViewModel() {

    /******** Getters and Setters *********/

    var callbacks: HomeFragmentCallbacks? = null
    var dataModel: HomeFragmentDataModel? = null
    var userProfile: UserProfile? = null


    /******** OnClick Listeners **********/

    fun onHamburgerClicked() {
        callbacks?.onHamburgerClicked()
    }

    /******* DataModel Requests *********/

    fun getUserProfile() {
        dataModel?.getUserProfile(this)
    }
}