package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.PowerUpsLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment

class PowerUpsFragment: BaseFragment<PowerUpsLayoutBinding, PowerUpsViewModel>(), PowerUpsCallbacks {


    override var viewModel: PowerUpsViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.power_ups_layout

    override var progressSpinner: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(PowerUpsViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }
}