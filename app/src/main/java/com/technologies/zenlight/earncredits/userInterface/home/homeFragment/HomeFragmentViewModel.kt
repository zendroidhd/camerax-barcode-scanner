package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel

class HomeFragmentViewModel: BaseViewModel() {

    /******** Getters and Setters *********/

    var callbacks: HomeFragmentCallbacks? = null


    /******** OnClick Listeners **********/

    fun onHamburgerClicked() {
        callbacks?.onHamburgerClicked()
    }
}