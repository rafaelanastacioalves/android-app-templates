package com.example.rafaelanastacioalves.moby.Dagger;

import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    AppRepository provideAppRepository(){
        return new AppRepository();
    }
}
