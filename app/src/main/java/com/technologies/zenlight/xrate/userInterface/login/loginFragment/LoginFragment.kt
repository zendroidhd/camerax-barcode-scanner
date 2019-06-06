package com.technologies.zenlight.xrate.userInterface.login.loginFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.xrate.R
import com.technologies.zenlight.xrate.databinding.FragmentContainerBinding
import com.technologies.zenlight.xrate.userInterface.base.BaseFragment

class LoginFragment : BaseFragment<FragmentContainerBinding, LoginFragmentViewModel>(), LoginFragmentCallbacks {

    private lateinit var viewModel: LoginFragmentViewModel

    override var mViewModel: LoginFragmentViewModel = viewModel

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.fragment_container

    override var progressSpinner: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel.callbacks = this
    }
}