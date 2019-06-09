package com.technologies.zenlight.earncredits.userInterface.login.loginFragment

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.technologies.zenlight.earncredits.BR
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.LoginHomeScreenBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import com.technologies.zenlight.earncredits.userInterface.login.forgotPassword.ForgotPasswordFragment
import com.technologies.zenlight.earncredits.userInterface.login.signUp.SignUpFragment
import com.technologies.zenlight.earncredits.utils.addFragmentFadeIn
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginHomeScreenBinding, LoginFragmentViewModel>(), LoginFragmentCallbacks {

    @Inject
    lateinit var appContext: Context


    override var viewModel: LoginFragmentViewModel? = null

    override var bindingVariable: Int = BR.viewModel

    override var layoutId: Int = R.layout.login_home_screen

    override var progressSpinner: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        viewModel?.callbacks = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        fadeInTitle()
        return dataBinding.root
    }

    private fun playSound(){
        val mediaPlayer : MediaPlayer = MediaPlayer.create(appContext, R.raw.opening_title)
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build())
        mediaPlayer.start()
    }


    private fun fadeInTitle() {
        playSound()
        YoYo.with(Techniques.FadeIn)
            .duration(3000)
            .repeatMode(0)
            .onStart{ dataBinding.layoutTitle.visibility = View.VISIBLE}
            .onEnd{ fadeInContent() }
            .playOn(dataBinding.layoutTitle)
    }

    private fun fadeInContent() {
        YoYo.with(Techniques.FadeIn)
            .duration(1500)
            .repeatMode(0)
            .onStart { animator -> dataBinding.layoutContent.visibility = View.VISIBLE }
            .onEnd{ animator -> fadeInInsertCoin()  }
            .playOn(dataBinding.layoutContent)
    }

    private fun fadeInInsertCoin() {
        dataBinding.btnInsertCoin.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_fade_out))
    }

    override fun onSignUpClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = SignUpFragment.newInstance()
            addFragmentFadeIn(fragment,manager,"SignUp",null)
        }
    }

    override fun onForgotPasswordClicked() {
        baseActivity?.let {
            val manager = it.supportFragmentManager
            val fragment = ForgotPasswordFragment.newInstance()
            addFragmentFadeIn(fragment,manager,"ForgotPassword",null)
        }
    }
}