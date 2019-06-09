package com.technologies.zenlight.earncredits.dependencyInjection.builder

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
}