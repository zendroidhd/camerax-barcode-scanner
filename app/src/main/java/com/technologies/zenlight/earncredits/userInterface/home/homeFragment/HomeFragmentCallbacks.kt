package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

interface HomeFragmentCallbacks {

    fun onHamburgerClicked()

    fun onUserProfileReturnedSuccessfully()

    fun handleError(title: String, body: String)

    fun queryData()

}