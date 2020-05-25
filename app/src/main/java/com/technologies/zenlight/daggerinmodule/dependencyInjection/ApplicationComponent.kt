package com.technologies.zenlight.daggerinmodule.dependencyInjection

import com.technologies.zenlight.daggerinmodule.ui.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class])
interface ApplicationComponent {


    /****** Activities ********/

    fun inject(mainActivity: MainActivity)


    /******* Component Builder ********/

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): ApplicationComponent
    }
}