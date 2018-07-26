package com.example.rafaelanastacioalves.moby.Dagger;

import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface RepositoryComponent {
    AppRepository provideAppRepositoryService();

}
