package com.example.rafaelanastacioalves.moby.di;

import com.example.rafaelanastacioalves.moby.entitymainlisting.EntityMainModule;
import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;
import com.example.rafaelanastacioalves.moby.entitymainlisting.ProjectViewModelFactory;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainEntityListingModule {

    @ContributesAndroidInjector(modules = EntityMainModule.class)
    abstract MainActivity bindMainActivity();


}
