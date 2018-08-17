package com.example.rafaelanastacioalves.moby.di;

import android.content.Context;

import com.example.rafaelanastacioalves.moby.application.MainApplication;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;
import com.example.rafaelanastacioalves.moby.entitymainlisting.ProjectViewModelFactory;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Context provideContext(MainApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    ProjectViewModelFactory projectViewModelFactory(AppRepository appRepository){
        return new ProjectViewModelFactory(appRepository);
    }


}
