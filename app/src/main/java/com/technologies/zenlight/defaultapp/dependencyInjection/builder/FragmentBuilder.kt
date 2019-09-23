package com.technologies.zenlight.defaultapp.dependencyInjection.builder

import com.technologies.zenlight.defaultapp.userInterface.login.forgotPassword.ForgotPasswordFragment
import com.technologies.zenlight.defaultapp.userInterface.login.loginFragment.LoginFragment
import com.technologies.zenlight.defaultapp.userInterface.login.signUp.SignUpFragment
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