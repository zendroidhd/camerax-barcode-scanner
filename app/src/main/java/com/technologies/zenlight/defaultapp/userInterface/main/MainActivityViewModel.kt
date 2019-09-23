package com.technologies.zenlight.defaultapp.userInterface.main

import com.technologies.zenlight.defaultapp.data.model.api.UserProfile
import com.technologies.zenlight.defaultapp.userInterface.base.BaseViewModel

class MainActivityViewModel : BaseViewModel() {

    var callbacks: MainActivityCallbacks? = null
    var userProfile: UserProfile? = null
}