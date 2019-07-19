package com.technologies.zenlight.earncredits.userInterface.home.mainMenu

import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel

class MainMenuViewModel: BaseViewModel() {

    /******** Getters and Setters ********/

    var callbacks: MainMenuCallbacks? = null

    /********* OnClick Listeners *********/

    fun onExitGameClicked(){
        callbacks?.onExitGameClicked()
    }

    fun onGameOptionsClicked() {
        callbacks?.onGameOptionsClicked()
    }

    fun onLeaderBoardsClicked() {
        callbacks?.onLeaderBoardsClicked()
    }

    fun onDailyCheatCodeClicked() {
        callbacks?.onDailyCheatCodeClicked()
    }

    fun onMyProfileClicked() {
        callbacks?.onMyProfileClicked()
    }
}