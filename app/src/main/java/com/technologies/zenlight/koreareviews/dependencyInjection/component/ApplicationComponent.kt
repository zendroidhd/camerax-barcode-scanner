package com.technologies.zenlight.koreareviews.dependencyInjection.component

import android.app.Application
import com.technologies.zenlight.koreareviews.dependencyInjection.Builder.ActivityBuilder
import com.technologies.zenlight.koreareviews.dependencyInjection.Builder.FragmentBuilder
import com.technologies.zenlight.koreareviews.dependencyInjection.Builder.ServiceBuilder
import com.technologies.zenlight.koreareviews.dependencyInjection.module.AppModule
import com.technologies.zenlight.koreareviews.dependencyInjection.module.DatabaseModule
import com.technologies.zenlight.koreareviews.dependencyInjection.module.NetworkModule
import com.technologies.zenlight.koreareviews.userInterface.ReviewApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class,
FragmentBuilder::class, ServiceBuilder::class, AppModule::class, NetworkModule::class, DatabaseModule::class])

interface ApplicationComponent {

    abstract fun inject(app: ReviewApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}