package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.ChallengesLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.ChallengesAdapter
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.HomeFragmentViewModel

class ChallengesFragment: BaseFragment<ChallengesLayoutBinding, ChallengesViewModel>() {

    override var viewModel: ChallengesViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.challenges_layout

    override var progressSpinner: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.rvChallenges.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ChallengesAdapter()
        }
        return dataBinding.root
    }
}