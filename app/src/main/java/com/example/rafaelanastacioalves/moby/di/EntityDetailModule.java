package com.example.rafaelanastacioalves.moby.di;

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment;
import com.example.rafaelanastacioalves.moby.entitymainlisting.EntityMainModule;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EntityDetailModule {

    @ContributesAndroidInjector(modules = com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailModule.class)
    abstract EntityDetailsFragment bindEntityDetailFragment();


}
