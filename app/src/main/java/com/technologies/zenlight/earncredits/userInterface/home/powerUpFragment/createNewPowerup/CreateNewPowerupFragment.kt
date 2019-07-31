package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.createNewPowerup

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.databinding.CreateNewPowerupLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.home.challenges.createNewChallenge.CreateChallengeFragment
import com.technologies.zenlight.earncredits.userInterface.home.homeActivity.HomeActivityCallbacks
import com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.PowerUpsCallbacks
import com.technologies.zenlight.earncredits.utils.REMOVE_NON_DIGITS
import com.technologies.zenlight.earncredits.utils.showAlertDialog
import javax.inject.Inject

class CreateNewPowerupFragment: BaseFragment<CreateNewPowerupLayoutBinding, CreatePowerupViewModel>(), CreatePowerupCallbacks{

    @Inject
    lateinit var dataModel: CreatePowerupDataModel

    private var parentCallbacks: HomeActivityCallbacks? = null

    override var viewModel: CreatePowerupViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.create_new_powerup_layout

    companion object {
        var powerUpCallbacks: PowerUpsCallbacks? = null
        fun newInstance(callback: PowerUpsCallbacks): CreateNewPowerupFragment {
            powerUpCallbacks = callback
            return CreateNewPowerupFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as HomeActivityCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CreatePowerupViewModel::class.java)
        viewModel?.callbacks = this
        viewModel?.dataModel = dataModel
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.etDescription.requestFocus()
        setUpSpinner()
    }

    override fun handleError(title: String, body: String) {
        parentCallbacks?.hideProgressSpinnerView()
        showAlertDialog(activity, title, body)
    }

    override fun getActivityContext(): Activity? {
        return activity
    }

    override fun onPowerupCreatedSuccessfully() {
        activity?.let {
            it.onBackPressed()
            powerUpCallbacks?.requestPowerUps()
        }
    }

    override fun onCreateNewPowerupClicked() {
        val description = dataBinding.etDescription.text.toString()
        val cost = dataBinding.spinnerCost.selectedItem.toString()
        var costInt = REMOVE_NON_DIGITS.replace(cost,"").toInt()

        if (costInt == 1020) { costInt = 20 }
        //val creditAmount =
        viewModel?.let {
            if (description.isNotEmpty()) {
                parentCallbacks?.showProgressSpinnerView()
                val powerUp = PowerUps()
                powerUp.description = description
                powerUp.cost = costInt
                viewModel?.createNewPowerup(powerUp)
            } else {
                val msg = "Please enter a description to create a new power-up"
                showAlertDialog(activity, "Missing Description", msg)
            }
        }
    }

    private fun setUpSpinner() {
        val adapter =
            ArrayAdapter.createFromResource(activity, R.array.powerups_cost_list, R.layout.white_spinner_layout)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        dataBinding.spinnerCost.adapter = adapter
    }
}