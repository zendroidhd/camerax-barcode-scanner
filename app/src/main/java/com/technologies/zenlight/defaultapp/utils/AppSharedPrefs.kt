package com.technologies.zenlight.defaultapp.utils

import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPrefs @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var userId: String?
        get() = sharedPreferences
            .getString(USER_ID, "")
        set(id) = sharedPreferences
            .edit()
            .putString(USER_ID, id)
            .apply()

    var userEmail: String?
        get() = sharedPreferences
            .getString(USER_EMAIL, "")
        set(id) = sharedPreferences
            .edit()
            .putString(USER_EMAIL, id)
            .apply()

    var userPassword: String?
        get() = sharedPreferences
            .getString(USER_PASSWORD, "")
        set(id) = sharedPreferences
            .edit()
            .putString(USER_PASSWORD, id)
            .apply()

    var isLoggedIn: Boolean
        get() = sharedPreferences
            .getBoolean(USER_LOGGED_IN,false)
        set(loggedIn) = sharedPreferences
            .edit()
            .putBoolean(USER_LOGGED_IN,loggedIn)
            .apply()

    var isLoggedInAsGuest: Boolean
        get() = sharedPreferences
            .getBoolean(LOGGED_IN_AS_GUEST,false)
        set(loggedIn) = sharedPreferences
            .edit()
            .putBoolean(LOGGED_IN_AS_GUEST,loggedIn)
            .apply()


    companion object {
        private const val USER_EMAIL = "userEmail"
        private const val USER_PASSWORD = "userPassword"
        private const val USER_ID = "userId"
        private const val USER_LOGGED_IN = "userLoggedIn"
        private const val LOGGED_IN_AS_GUEST = "loggedInAsGuest"

    }
}