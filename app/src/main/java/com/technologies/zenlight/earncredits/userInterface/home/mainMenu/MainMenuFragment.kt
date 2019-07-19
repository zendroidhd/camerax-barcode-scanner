package com.technologies.zenlight.earncredits.userInterface.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.MainMenuLayoutBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.utils.showSignOutAlertDialog
import com.technologies.zenlight.earncredits.utils.showToastShort

class MainMenuFragment : BaseFragment<MainMenuLayoutBinding, MainMenuViewModel>(), MainMenuCallbacks {


    companion object {
        fun newInstance(): MainMenuFragment {
            return MainMenuFragment()
        }
    }

    override var viewModel: MainMenuViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.main_menu_layout

    override var progressSpinner: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MainMenuViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onExitGameClicked() {
       showSignOutAlertDialog(activity, ::exitGame)
    }

    override fun onGameOptionsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLeaderBoardsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDailyCheatCodeClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMyProfileClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun exitGame() {
        dataManager.getSharedPrefs().isLoggedIn = false
        activity?.let {
            it.finishAffinity()
            showToastShort(activity,"Innovate, Create, and Keep Grinding")
        }
    }
}