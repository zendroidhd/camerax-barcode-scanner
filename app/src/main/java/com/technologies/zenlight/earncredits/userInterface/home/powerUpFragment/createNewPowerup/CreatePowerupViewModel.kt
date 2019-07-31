package com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.createNewPowerup

import androidx.lifecycle.ViewModel
import com.technologies.zenlight.earncredits.data.model.api.PowerUps
import com.technologies.zenlight.earncredits.userInterface.base.BaseViewModel
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_BODY
import com.technologies.zenlight.earncredits.utils.NO_NETWORK_TITLE
import com.technologies.zenlight.earncredits.utils.isConnected

class CreatePowerupViewModel: BaseViewModel() {

    /******** Getters and Setters ********/

    var callbacks: CreatePowerupCallbacks? = null
    var dataModel: CreatePowerupDataModel? = null

    /******** OnClick Listeners *********/

    fun onCreateNewPowerupClicked(){
        callbacks?.onCreateNewPowerupClicked()
    }

    /******** DataModel Requests *********/

    fun createNewPowerup(powerUp: PowerUps) {
        callbacks?.getActivityContext()?.let {activity->
            if (isConnected(activity)){
                dataModel?.createNewPowerup(this,powerUp)
            } else {
                callbacks?.handleError(NO_NETWORK_TITLE, NO_NETWORK_BODY)
            }
        }
    }
}