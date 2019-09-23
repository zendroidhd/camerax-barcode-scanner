package com.technologies.zenlight.defaultapp.dependencyInjection.component

import android.app.Application
import com.technologies.zenlight.defaultapp.dependencyInjection.builder.ActivityBuilder
import com.technologies.zenlight.defaultapp.dependencyInjection.builder.FragmentBuilder
import com.technologies.zenlight.defaultapp.dependencyInjection.builder.ServiceBuilder
import com.technologies.zenlight.defaultapp.dependencyInjection.module.AppModule
import com.technologies.zenlight.defaultapp.dependencyInjection.module.DatabaseModule
import com.technologies.zenlight.defaultapp.dependencyInjection.module.NetworkModule
import com.technologies.zenlight.defaultapp.userInterface.AppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class,
FragmentBuilder::class, ServiceBuilder::class, AppModule::class, NetworkModule::class, DatabaseModule::class])

interface ApplicationComponent {

     fun inject(app: AppApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}