package com.example.rafaelanastacioalves.moby.di;

import android.app.Application;

import com.example.rafaelanastacioalves.moby.application.MainApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, MainEntityListingModule.class, ApplicationModule.class})
public interface MyApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MyApplicationComponent build();
    }

    void inject(MainApplication application);
}
