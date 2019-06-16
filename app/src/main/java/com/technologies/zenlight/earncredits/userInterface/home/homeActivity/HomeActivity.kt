package com.technologies.zenlight.earncredits.userInterface.home.homeActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.FragmentContainerBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseActivity
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.HomeFragment

class HomeActivity : BaseActivity<FragmentContainerBinding, HomeActivityViewModel>(), HomeActivityCallbacks {


    override var viewModel: HomeActivityViewModel? = null

    override var bindingVariable: Int = 0

    override var layoutId: Int = R.layout.fragment_container

    override var progressSpinner: View? = null

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
        instantiateFragment()
    }


    private fun instantiateFragment() {
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = HomeFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}