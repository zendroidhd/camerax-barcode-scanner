package com.technologies.zenlight.earncredits.userInterface.login.loginActivity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.data.AppDataManager
import com.technologies.zenlight.earncredits.databinding.FragmentContainerBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseActivity
import com.technologies.zenlight.earncredits.userInterface.login.loginFragment.LoginFragment
import javax.inject.Inject

class LoginActivity : BaseActivity<FragmentContainerBinding, LoginActivityViewModel>(), LoginActivityCallbacks {


    override var viewModel: LoginActivityViewModel? = null

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.fragment_container

    override var progressSpinner: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        progressSpinner = dataBinding.layoutProgress.progressSpinner
        viewModel?.callbacks = this
        instantiateFragment()
    }

    private fun instantiateFragment() {
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = LoginFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun showProgressSpinnerView() {
        showProgressSpinner()
    }

    override fun hideProgressSpinnerView() {
        hideProgressSpinner()
    }

}