package com.example.rafaelanastacioalves.moby.entitymainlisting;

import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityMainModule {


    @Provides
    ProjectViewModelFactory projectViewModelFactory(MainEntityListInteractor mainEntityListinInteractor){
        return new ProjectViewModelFactory(mainEntityListinInteractor);
    }

}
