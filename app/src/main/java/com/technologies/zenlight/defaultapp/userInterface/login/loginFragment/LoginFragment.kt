package com.technologies.zenlight.defaultapp.userInterface.login.loginFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.defaultapp.BR
import com.technologies.zenlight.defaultapp.R
import com.technologies.zenlight.defaultapp.databinding.LoginHomeScreenBinding
import com.technologies.zenlight.defaultapp.userInterface.base.BaseFragment
import com.technologies.zenlight.defaultapp.userInterface.login.forgotPassword.ForgotPasswordFragment
import com.technologies.zenlight.defaultapp.userInterface.login.loginActivity.LoginActivityCallbacks
import com.technologies.zenlight.defaultapp.userInterface.login.signUp.SignUpFragment
import com.technologies.zenlight.defaultapp.utils.addFragmentFadeIn
import com.technologies.zenlight.defaultapp.utils.showAlertDialog
import com.technologies.zenlight.defaultapp.utils.showForgotPasswordAlertDialog
import com.technologies.zenlight.defaultapp.utils.showSignUpAlertDialog
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginHomeScreenBinding, LoginFragmentViewModel>(), LoginFragmentCallbacks {

    @Inject
    lateinit var dataModel: LoginFragmentDataModel


    private var parentCallbacks: LoginActivityCallbacks? = null

    override var viewModel: LoginFragmentViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.login_home_screen


    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as LoginActivityCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
        viewModel?.dataModel = dataModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()
        populateSavedCredentials()
    }


    override fun handleError(title: String, body: String) {
       parentCallbacks?.hideProgressSpinnerView()
        showAlertDialog(activity,title,body)
    }

    override fun showSignUpAlert() {
        parentCallbacks?.hideProgressSpinnerView()
        showSignUpAlertDialog(activity,::onSignUpClicked)
    }

    override fun showForgotPasswordAlert() {
        parentCallbacks?.hideProgressSpinnerView()
        showForgotPasswordAlertDialog(activity,::onForgotPasswordClicked)
    }

    override fun onCredentialsReturnedSuccessfully() {
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()
        viewModel?.signUserIntoFirebase(email,passWord)
    }

    override fun signUserIntoApp() {
        parentCallbacks?.hideProgressSpinnerView()
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()

        dataManager.getSharedPrefs().isLoggedIn = true
        dataManager.getSharedPrefs().userEmail = email
        dataManager.getSharedPrefs().userPassword = passWord
        context?.let {
            baseActivity?.finish()
       //     baseActivity?.startActivity(HomeActivity.newIntent(it))
        }
    }

    override fun getActivityContext(): Activity? {
        return activity
    }

    override fun onSignUpClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = SignUpFragment.newInstance()
            addFragmentFadeIn(fragment,manager,"SignUp",null)
        }
    }

    override fun onForgotPasswordClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = ForgotPasswordFragment.newInstance()
            addFragmentFadeIn(fragment,manager,"ForgotPassword",null)
        }
    }

    override fun onSignInButtonClicked() {
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()
        viewModel?.let {
            if (!it.isLoginValuesValid(email, passWord)) {
                showAlertDialog(activity,"Invalid Credentials",it.getEmptyLoginValuesText(email,passWord))
            } else {
                parentCallbacks?.showProgressSpinnerView()
                it.submitLoginCredentials(email,passWord)
            }
        }
    }

    private fun populateSavedCredentials() {
        dataBinding.etEmail.setText(dataManager.getSharedPrefs().userEmail)
        dataBinding.etCode.setText(dataManager.getSharedPrefs().userPassword)
    }


}