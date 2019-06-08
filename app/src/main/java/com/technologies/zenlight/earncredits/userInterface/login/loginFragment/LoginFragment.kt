package com.technologies.zenlight.earncredits.userInterface.login.loginFragment

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.technologies.zenlight.earncredits.R
import com.technologies.zenlight.earncredits.databinding.LoginHomeScreenBinding
import com.technologies.zenlight.earncredits.userInterface.base.BaseFragment
import kotlinx.android.synthetic.main.login_home_screen.view.*
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginHomeScreenBinding, LoginFragmentViewModel>(), LoginFragmentCallbacks {

    @Inject
    lateinit var appContext: Context

    private var viewModel: LoginFragmentViewModel? = null

    override var mViewModel: LoginFragmentViewModel? = viewModel

    override var bindingVariable: Int = 0

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
        return binding.root
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
            .onStart{ binding.layoutTitle.visibility = View.VISIBLE}
            .onEnd{ fadeInContent() }
            .playOn(binding.layoutTitle)
    }

    private fun fadeInContent() {
        YoYo.with(Techniques.FadeIn)
            .duration(1500)
            .repeatMode(0)
            .onStart { animator -> binding.layoutContent.visibility = View.VISIBLE }
            .onEnd{ animator -> fadeInInsertCoin()  }
            .playOn(binding.layoutContent)
    }

    private fun fadeInInsertCoin() {
        binding.btnInsertCoin.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_fade_out))
    }

    override fun onSignUpClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onForgotPasswordClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}