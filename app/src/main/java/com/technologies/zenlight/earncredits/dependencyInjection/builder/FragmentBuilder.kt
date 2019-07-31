package com.technologies.zenlight.earncredits.dependencyInjection.builder

import com.technologies.zenlight.earncredits.userInterface.home.challenges.ChallengesFragment
import com.technologies.zenlight.earncredits.userInterface.home.challenges.createNewChallenge.CreateChallengeFragment
import com.technologies.zenlight.earncredits.userInterface.home.homeFragment.HomeFragment
import com.technologies.zenlight.earncredits.userInterface.home.mainMenu.MainMenuFragment
import com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.PowerUpsFragment
import com.technologies.zenlight.earncredits.userInterface.home.powerUpFragment.createNewPowerup.CreateNewPowerupFragment
import com.technologies.zenlight.earncredits.userInterface.login.forgotPassword.ForgotPasswordFragment
import com.technologies.zenlight.earncredits.userInterface.login.loginFragment.LoginFragment
import com.technologies.zenlight.earncredits.userInterface.login.signUp.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun bindSignUpFragment(): SignUpFragment

    @ContributesAndroidInjector
    internal abstract fun bindForgotPasswordFragment(): ForgotPasswordFragment

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindChallengesFragment(): ChallengesFragment

    @ContributesAndroidInjector
    internal abstract fun bindPowerUpsFragment(): PowerUpsFragment

    @ContributesAndroidInjector
    internal abstract fun bindMainMenuFragment(): MainMenuFragment

    @ContributesAndroidInjector
    internal abstract fun bindCreateChallengeFragment(): CreateChallengeFragment

    @ContributesAndroidInjector
    internal abstract fun bindCreateNewPowerupFragment(): CreateNewPowerupFragment
}