package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.data.model.api.Challenges
import com.technologies.zenlight.earncredits.databinding.ChallengesLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.home.challengesFragment.createNewChallenge.CreateChallengeFragment
import com.technologies.zenlight.earncredits.userInterface.home.homeActivity.HomeActivityCallbacks
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.HomeFragmentCallbacks
import com.technologies.zenlight.earncredits.userInterface.login.signUp.SignUpFragment
import com.technologies.zenlight.earncredits.utils.addFragmentFadeIn
import com.technologies.zenlight.earncredits.utils.showAlertDialog
import okhttp3.Challenge
import javax.inject.Inject

class ChallengesFragment: BaseFragment<ChallengesLayoutBinding, ChallengesViewModel>(), ChallengesCallbacks {

    @Inject
    lateinit var dataModel: ChallengesDataModel

    private var challengesAdapter: ChallengesAdapter? = null
    private val challengesList = ArrayList<Challenges>()
    private var parentCallbacks: HomeActivityCallbacks? = null

    override var viewModel: ChallengesViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.challenges_layout

    companion object {
        private var homeFragmentCallbacks: HomeFragmentCallbacks? = null
        fun newInstance(callback: HomeFragmentCallbacks) : ChallengesFragment {
            homeFragmentCallbacks = callback
            return ChallengesFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCallbacks = context as HomeActivityCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
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
        setUpRecyclerView()
        requestsChallenges()

        dataBinding.swipeContainer.setOnRefreshListener {
            parentCallbacks?.let {
                if (it.isLoading()) {
                    dataBinding.swipeContainer.isRefreshing = false
                } else {
                    viewModel?.getAllChallenges()
                }
            }
        }
    }

    override fun handleError(title: String, body: String) {
        hideAllProgressSpinners()
        showAlertDialog(activity,title,body)
    }

    override fun onChallengesReturnedSuccessfully() {
        hideAllProgressSpinners()
        dataBinding.layoutEmptyPowerUps.visibility = View.GONE
        challengesList.clear()
        viewModel?.let {
            challengesList.addAll(it.challengesList)
            challengesAdapter?.notifyDataSetChanged()
        }
    }

    override fun showNoChallengesFoundPage() {
        hideAllProgressSpinners()
        dataBinding.layoutEmptyPowerUps.visibility = View.VISIBLE
    }

    override fun onAddNewChallengeClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = CreateChallengeFragment.newInstance(this)
            addFragmentFadeIn(fragment,manager,"CreateChallenge",null)
        }
    }

    override fun getActivityContext(): Activity? {
        return activity
    }

    override fun requestsChallenges() {
        parentCallbacks?.showProgressSpinnerView()
        viewModel?.getAllChallenges()
        homeFragmentCallbacks?.queryData()
    }

    private fun setUpRecyclerView() {
        challengesAdapter = ChallengesAdapter(challengesList, this)
        dataBinding.rvChallenges.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = challengesAdapter
        }
    }

    private fun hideAllProgressSpinners() {
        parentCallbacks?.hideProgressSpinnerView()
        dataBinding.swipeContainer.isRefreshing = false
    }


}