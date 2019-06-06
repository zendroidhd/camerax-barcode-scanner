package com.technologies.zenlight.earncredits.dependencyInjection.builder

import com.technologies.zenlight.earncredits.userInterface.login.loginFragment.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindLoginFragment(): LoginFragment
}