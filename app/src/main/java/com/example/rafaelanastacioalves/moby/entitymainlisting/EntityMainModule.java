package com.example.rafaelanastacioalves.moby.entitymainlisting;

import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityMainModule {



    @Provides
    ProjectViewModelFactory projectViewModelFactory(AppRepository appRepository){
        return new ProjectViewModelFactory(appRepository);
    }

}
