package com.technologies.zenlight.earncredits.userInterface.login.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.ForgotPasswordLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.login.signUp.SignUpViewModel
import javax.inject.Inject

class ForgotPasswordFragment : BaseFragment<ForgotPasswordLayoutBinding, ForgotPasswordViewModel>(), ForgotPasswordCallbacks {

    @Inject
    lateinit var dataModel: ForgotPasswordDataModel

    override var viewModel: ForgotPasswordViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.forgot_password_layout

    override var progressSpinner: View? = null

    companion object {
        fun newInstance(): ForgotPasswordFragment = ForgotPasswordFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
        viewModel?.dataModel = dataModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.etEmail.requestFocus()

        return  dataBinding.root
    }

    override fun onExitButtonClicked() {
        baseActivity?.onBackPressed()
    }

    override fun onResetButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}