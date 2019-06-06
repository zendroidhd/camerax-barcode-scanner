package com.technologies.zenlight.earncredits.userInterface.login.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.LoginHomeScreenBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment

class LoginFragment : BaseFragment<LoginHomeScreenBinding, LoginFragmentViewModel>(), LoginFragmentCallbacks {

    private var viewModel: LoginFragmentViewModel? = null

    override var mViewModel: LoginFragmentViewModel? = viewModel

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.login_home_screen

    override var progressSpinner: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        return dataBinding.root
    }
}