package com.technologies.zenlight.defaultapp.userInterface.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.defaultapp.BR
import com.technologies.zenlight.defaultapp.R
import com.technologies.zenlight.defaultapp.data.AppDataManager
import com.technologies.zenlight.defaultapp.databinding.FragmentContainerBinding
import com.technologies.zenlight.defaultapp.userInterface.base.BaseActivity
import com.technologies.zenlight.defaultapp.utils.showAlertDialog
import javax.inject.Inject

class MainActivity : BaseActivity<FragmentContainerBinding, MainActivityViewModel>(), MainActivityCallbacks {


    @Inject
    lateinit var manager: AppDataManager


    override var viewModel: MainActivityViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.fragment_container

    override var progressSpinner: View? = null

    private var isLoadingData = false

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
        progressSpinner = dataBinding.layoutProgress.progressSpinner
    }


    override fun isLoading(): Boolean {
        return isLoading()
    }

    override fun showProgressSpinnerView() {
        showProgressSpinner()
        isLoadingData = true
    }

    override fun hideProgressSpinnerView() {
        hideProgressSpinner()
        isLoadingData = false
    }

    override fun getAppContext(): Context {
        return applicationContext
    }

    override fun handleError(title: String, body: String) {
        hideProgressSpinnerView()
        showAlertDialog(this, title, body)
    }
}