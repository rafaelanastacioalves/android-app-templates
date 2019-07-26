package com.example.rafaelanastacioalves.moby.di;

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity;
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment;
import com.example.rafaelanastacioalves.moby.entitymainlisting.EntityMainModule;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainActivity;

import javax.inject.Scope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EntityDetailingModule {

    @ContributesAndroidInjector
    abstract EntityDetailActivity bindEntityDetailActivity();


    @ContributesAndroidInjector(modules = com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailModule.class)
    abstract EntityDetailsFragment bindEntityDetailFragment();


}
