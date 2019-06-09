package com.technologies.zenlight.earncredits.userInterface.login.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.SignUpLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import javax.inject.Inject

class SignUpFragment : BaseFragment<SignUpLayoutBinding, SignUpViewModel>(), SignUpCallbacks{

    @Inject
    lateinit var dataModel: SignUpDataModel

    override var viewModel: SignUpViewModel? = null

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.sign_up_layout

    override var progressSpinner: View? = null

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
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
}