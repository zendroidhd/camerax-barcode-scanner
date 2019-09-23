package com.technologies.zenlight.defaultapp.userInterface.login.signUp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.defaultapp.BR
import com.technologies.zenlight.defaultapp.R
import com.technologies.zenlight.defaultapp.databinding.SignUpLayoutBinding
import com.technologies.zenlight.defaultapp.userInterface.base.BaseFragment
import com.technologies.zenlight.defaultapp.userInterface.login.loginActivity.LoginActivityCallbacks
import com.technologies.zenlight.defaultapp.utils.showAlertDialog
import com.technologies.zenlight.defaultapp.utils.showEmailAlreadyInUseAlertDialog
import javax.inject.Inject

class SignUpFragment : BaseFragment<SignUpLayoutBinding, SignUpViewModel>(), SignUpCallbacks{

    @Inject
    lateinit var dataModel: SignUpDataModel

    private var parentCallbacks: LoginActivityCallbacks? = null

    override var viewModel: SignUpViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.sign_up_layout


    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as LoginActivityCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
        viewModel?.dataModel = dataModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun handleError(title: String, body: String) {
        parentCallbacks?.hideProgressSpinnerView()
        showAlertDialog(activity, title, body)
    }

    override fun onStartNewGameClicked() {
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()
        val reEnteredPassword = dataBinding.etReEnterCode.text.toString()
        viewModel?.let { it ->
            if (!it.isCredentialsValid(email, passWord, reEnteredPassword)) {
                showAlertDialog(activity, "Invalid Credentials", it.getInvalidCredentialsText(email, passWord, reEnteredPassword))
            } else {
                parentCallbacks?.showProgressSpinnerView()
                it.submitLoginCredentials(email, passWord)
            }
        }
    }

    override fun onExitClicked() {
        activity?.onBackPressed()
    }

    override fun showEmailAlreadyInUseAlert() {
        parentCallbacks?.hideProgressSpinnerView()
        showEmailAlreadyInUseAlertDialog(activity,::goBackToLoginScreen)
    }

    override fun onUserAddedToFirebaseDatabase() {
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()
        viewModel?.createFirebaseUser(email,passWord)
    }

    override fun signUserIntoApp() {
        parentCallbacks?.hideProgressSpinnerView()
        activity?.let { activity ->
            saveCredentials()
            activity.finish()
          //  context?.let { baseActivity?.startActivity(HomeActivity.newIntent(activity)) }
        }
    }

    override fun getActivityContext(): Activity? {
        return activity
    }

    private fun saveCredentials() {
        val email = dataBinding.etEmail.text.toString()
        val passWord = dataBinding.etCode.text.toString()
        dataManager.getSharedPrefs().userEmail = email
        dataManager.getSharedPrefs().isLoggedIn = true
        dataManager.getSharedPrefs().userPassword = passWord
    }

    private fun goBackToLoginScreen() {
        activity?.onBackPressed()
    }
}