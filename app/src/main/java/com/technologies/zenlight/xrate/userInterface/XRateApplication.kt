package com.technologies.zenlight.xrate.userInterface

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.lifecycle.LifecycleObserver
import com.technologies.zenlight.xrate.data.AppDataManager
import com.technologies.zenlight.xrate.dependencyInjection.component.ApplicationComponent
import com.technologies.zenlight.xrate.dependencyInjection.component.DaggerApplicationComponent
import dagger.android.*
import javax.inject.Inject

class XRateApplication : Application(), HasActivityInjector, HasServiceInjector, LifecycleObserver {

    companion object {
        var isAppDestroyed = false
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var dataManager: AppDataManager

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector()
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceInjector()
    }

}