package com.technologies.zenlight.daggerinmodule.dependencyInjection

import android.content.Context

object DaggerInjector {

    private var component: ApplicationComponent? = null

    fun getComponent(context: Context): ApplicationComponent {
        if (component == null) {
            buildComponent(context)
        }
        return component!!
    }


    private fun buildComponent(context: Context) {
        component = DaggerApplicationComponent
            .builder()
            .appModule(AppModule(context))
            .build()
    }
}