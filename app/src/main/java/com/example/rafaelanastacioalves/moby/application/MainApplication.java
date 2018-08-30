package com.example.rafaelanastacioalves.moby.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.rafaelanastacioalves.moby.BuildConfig;
import com.example.rafaelanastacioalves.moby.di.DaggerMyApplicationComponent;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;


public class MainApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        setupLog();

        DaggerMyApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


    }

    private void setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Picasso.get()
                    .setIndicatorsEnabled(true);
            Picasso.get()
                    .setLoggingEnabled(true);
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentAndroidInjector;
    }

    public HasSupportFragmentInjector

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
