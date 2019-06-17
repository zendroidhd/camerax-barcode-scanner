package com.technologies.zenlight.earncredits.userInterface.home.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.HomeLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.home.challengesFragment.ChallengesFragment
import com.technologies.zenlight.earncredits.userInterface.home.mainMenu.MainMenuFragment
import com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.PowerUpsFragment
import com.technologies.zenlight.earncredits.utils.addFragmentHorizontally
import com.technologies.zenlight.earncredits.utils.replaceFragmentHorizontally
import com.technologies.zenlight.earncredits.utils.replaceFragmentHorizontallyReversed

class HomeFragment : BaseFragment<HomeLayoutBinding, HomeFragmentViewModel>(), HomeFragmentCallbacks {

    private var pagerAdapter: PagerAdapter? = null

    override var viewModel: HomeFragmentViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.home_layout

    override var progressSpinner: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        activity?.let {
            pagerAdapter = PagerAdapter(childFragmentManager)
        }
        dataBinding.vpMain.adapter = pagerAdapter
        dataBinding.viewpagertab.setupWithViewPager(dataBinding.vpMain)
        return dataBinding.root
    }

    override fun onHamburgerClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = MainMenuFragment.newInstance()
            replaceFragmentHorizontallyReversed(fragment, manager, "MainMenuFragment", null)
        }
    }

    private class PagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                ChallengesFragment()
            } else {
                PowerUpsFragment()
            }
        }

        override fun getCount() = 2

    }
}