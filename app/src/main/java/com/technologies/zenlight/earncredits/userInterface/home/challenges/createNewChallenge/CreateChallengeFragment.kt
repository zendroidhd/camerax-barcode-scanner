package com.technologies.zenlight.earncredits.userInterface.home.challenges.createNewChallenge

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
import com.technologies.zenlight.earncredits.databinding.CreateNewChallengeLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.home.challenges.ChallengesCallbacks
import com.technologies.zenlight.earncredits.userInterface.home.homeActivity.HomeActivityCallbacks
import com.technologies.zenlight.earncredits.utils.REMOVE_NON_DIGITS
import com.technologies.zenlight.earncredits.utils.dateFormatter
import com.technologies.zenlight.earncredits.utils.showAlertDialog
import java.util.*
import javax.inject.Inject

class CreateChallengeFragment : BaseFragment<CreateNewChallengeLayoutBinding, CreateChallengeViewModel>()
    , CreateChallengeCallbacks, DateListener {

    @Inject
    lateinit var dataModel: CreateChallengeDataModel

    private var parentCallbacks: HomeActivityCallbacks? = null

    override var viewModel: CreateChallengeViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.create_new_challenge_layout

    companion object {
        var challengesCallbacks: ChallengesCallbacks? = null
        fun newInstance(callback: ChallengesCallbacks): CreateChallengeFragment {
            challengesCallbacks = callback
            return CreateChallengeFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as HomeActivityCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CreateChallengeViewModel::class.java)
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
        setInitialCompletionDate()
    }

    override fun handleError(title: String, body: String) {
        parentCallbacks?.hideProgressSpinnerView()
        showAlertDialog(activity, title, body)
    }

    override fun onChallengeCreatedSuccessfully() {
        activity?.let {
            it.onBackPressed()
            challengesCallbacks?.requestsChallenges()
        }
    }

    override fun onCreateNewChallengeClicked() {
        val description = dataBinding.etDescription.text.toString()
        val createdTimeStamp: Long = System.currentTimeMillis() / 1000
        val credits = dataBinding.spinnerCredits.selectedItem.toString()
        var creditInt = REMOVE_NON_DIGITS.replace(credits,"").toInt()

        if (creditInt == 1020) { creditInt = 20 }
        //val creditAmount =
        viewModel?.let {
            if (it.isRequiredValuesFilled(description)) {
                parentCallbacks?.showProgressSpinnerView()
                val challenge = Challenges()
                challenge.description = description
                challenge.createdOn = createdTimeStamp
                challenge.completeBy = it.completeByTimestamp ?: createdTimeStamp
                challenge.credit = creditInt
                viewModel?.createNewChallenge(challenge)
            } else {
                val msg = "Please enter a description to create a new challenge"
                showAlertDialog(activity, "Missing Description", msg)
            }
        }
    }

    override fun onSetDateClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val dialog = DatePickerFragment.newInstance(this)
            dialog.show(manager, "DatePicker")
        }
    }

    override fun getActivityContext(): Activity? {
        return activity
    }

    override fun onDateSet(date: String, timestamp: Long) {
        dataBinding.btnCompletionDate.text = date
        viewModel?.completeByTimestamp = timestamp / 1000
    }

    private fun setUpSpinner() {
        val adapter =
            ArrayAdapter.createFromResource(activity, R.array.credits_amount_list, R.layout.white_spinner_layout)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        dataBinding.spinnerCredits.adapter = adapter
    }

    private fun setInitialCompletionDate() {
        val date = Date()
        val time = dateFormatter.format(date)
        dataBinding.btnCompletionDate.text = time
    }
}