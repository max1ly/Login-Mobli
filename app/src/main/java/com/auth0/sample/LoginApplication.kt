package com.auth0.sample

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun isLoggable(tag: String?, priority: Int): Boolean {
                return BuildConfig.DEBUG || priority >= Log.ERROR
            }
        })
    }
}
