package com.technologies.zenlight.defaultapp.userInterface.main

import android.content.Context

interface MainActivityCallbacks {

    fun isLoading(): Boolean

    fun showProgressSpinnerView()

    fun hideProgressSpinnerView()

    fun getAppContext(): Context

    fun handleError(title: String, body: String)
}