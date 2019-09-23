package com.technologies.zenlight.defaultapp.userInterface.login.forgotPassword

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.defaultapp.BR
import com.technologies.zenlight.defaultapp.R
import com.technologies.zenlight.defaultapp.databinding.ForgotPasswordLayoutBinding
import com.technologies.zenlight.defaultapp.userInterface.base.BaseFragment
import com.technologies.zenlight.defaultapp.userInterface.login.loginActivity.LoginActivityCallbacks
import com.technologies.zenlight.defaultapp.utils.isEmailValid
import com.technologies.zenlight.defaultapp.utils.showAlertDialog
import com.technologies.zenlight.defaultapp.utils.showPasswordResetAlertDialog
import javax.inject.Inject

class ForgotPasswordFragment : BaseFragment<ForgotPasswordLayoutBinding, ForgotPasswordViewModel>(), ForgotPasswordCallbacks {

    @Inject
    lateinit var dataModel: ForgotPasswordDataModel

    private var parentCallbacks: LoginActivityCallbacks? = null

    override var viewModel: ForgotPasswordViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.forgot_password_layout


    companion object {
        fun newInstance(): ForgotPasswordFragment = ForgotPasswordFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as LoginActivityCallbacks
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

    override fun handleError(title: String, body: String) {
        parentCallbacks?.hideProgressSpinnerView()
        showAlertDialog(activity, title, body)
    }

    override fun showEmailNotFoundAlert() {
        parentCallbacks?.hideProgressSpinnerView()
        val title = "Invalid Email"
        val body = "The submitted email was not found"
        showAlertDialog(activity,title,body,"Try Again")
    }

    override fun showPasswordResetAlert() {
        parentCallbacks?.hideProgressSpinnerView()
        showPasswordResetAlertDialog(activity,::onExitButtonClicked)
    }


    override fun onExitButtonClicked() {
        baseActivity?.onBackPressed()
    }

    override fun onResetButtonClicked() {
        val email = dataBinding.etEmail.text.toString()

        if (isEmailValid(email)) {
            parentCallbacks?.showProgressSpinnerView()
            viewModel?.submitUserCredentials(email)

        } else {
            val title = "Invalid Email"
            val body = "Please enter a valid email"
            showAlertDialog(activity,title,body,"Try Again")
        }
    }

    override fun getActivityContext(): Activity? {
        return activity
    }
}