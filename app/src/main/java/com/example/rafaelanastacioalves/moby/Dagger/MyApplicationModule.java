package com.example.rafaelanastacioalves.moby.Dagger;

import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyApplicationModule {

    @ContributesAndroidInjector
    abstract LiveDataMainEntityListViewModel contributeActivityInjector();

}
