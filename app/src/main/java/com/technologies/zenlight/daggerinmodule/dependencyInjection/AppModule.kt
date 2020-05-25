package com.technologies.zenlight.daggerinmodule.dependencyInjection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }
}