package com.technologies.zenlight.xrate.userInterface.login.loginActivity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.xrate.R
import com.technologies.zenlight.xrate.databinding.FragmentContainerBinding
import com.technologies.zenlight.xrate.userInterface.base.BaseActivity
import java.io.File

class LoginActivity : BaseActivity<FragmentContainerBinding, LoginActivityViewModel>(), LoginActivityCallbacks {

    private lateinit var viewModel : LoginActivityViewModel

    override var mViewModel: LoginActivityViewModel = viewModel

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.fragment_container

    override var progressSpinner: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel.callbacks = this

    }

}