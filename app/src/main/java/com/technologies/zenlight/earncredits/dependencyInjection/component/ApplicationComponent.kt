package com.technologies.zenlight.earncredits.dependencyInjection.component

import android.app.Application
import com.technologies.zenlight.earncredits.dependencyInjection.builder.ActivityBuilder
import com.technologies.zenlight.earncredits.dependencyInjection.builder.FragmentBuilder
import com.technologies.zenlight.earncredits.dependencyInjection.builder.ServiceBuilder
import com.technologies.zenlight.earncredits.dependencyInjection.module.AppModule
import com.technologies.zenlight.earncredits.dependencyInjection.module.DatabaseModule
import com.technologies.zenlight.earncredits.dependencyInjection.module.NetworkModule
import com.technologies.zenlight.earncredits.userInterface.XRateApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class,
FragmentBuilder::class, ServiceBuilder::class, AppModule::class, NetworkModule::class, DatabaseModule::class])

interface ApplicationComponent {

     fun inject(app: XRateApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}