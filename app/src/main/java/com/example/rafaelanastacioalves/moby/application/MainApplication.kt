package com.example.rafaelanastacioalves.moby.application

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import android.util.Log

import com.example.rafaelanastacioalves.moby.BuildConfig
import com.squareup.picasso.Picasso

import timber.log.Timber


class MainApplication : Application() {
    override fun onCreate() {
        setupLog()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
    }

    private fun setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Picasso.get()
                    .setIndicatorsEnabled(true)
            Picasso.get().isLoggingEnabled = true
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            FakeCrashLibrary.log(priority, tag, message)

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t)
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t)
                }
            }
        }
    }
}
