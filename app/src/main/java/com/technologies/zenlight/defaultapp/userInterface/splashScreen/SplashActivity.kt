package com.technologies.zenlight.defaultapp.userInterface.splashScreen

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.technologies.zenlight.defaultapp.R
import com.technologies.zenlight.defaultapp.databinding.SplashActivityLayoutBinding
import com.technologies.zenlight.defaultapp.userInterface.base.BaseActivity
import com.technologies.zenlight.defaultapp.userInterface.login.loginActivity.LoginActivity
import com.technologies.zenlight.defaultapp.userInterface.main.MainActivity

class SplashActivity: BaseActivity<SplashActivityLayoutBinding, SplashViewModel>() {

    override var viewModel: SplashViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.splash_activity_layout

    override var progressSpinner: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (dataManager.getSharedPrefs().isLoggedIn){
            finishAffinity()
            startActivity(MainActivity.newIntent(this))
        } else {
            finishAffinity()
            startActivity(LoginActivity.newIntent(this))
        }
    }
}