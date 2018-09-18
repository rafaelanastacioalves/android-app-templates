package com.example.rafaelanastacioalves.moby.entitydetailing;

import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailingInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityDetailModule {

    @Provides
    EntityDetailViewModelFactory buildEntityDetailViewModelFactory(EntityDetailingInteractor entityDetailingInteractor){
        return new EntityDetailViewModelFactory(entityDetailingInteractor);
    }
}
