package com.example.rafaelanastacioalves.moby.entitydetailing;

import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailaingInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityDetailModule {

    @Provides
    EntityDetailViewModelFactory buildEntityDetailViewModelFactory(EntityDetailaingInteractor entityDetailaingInteractor){
        return new EntityDetailViewModelFactory(entityDetailaingInteractor);
    }
}
