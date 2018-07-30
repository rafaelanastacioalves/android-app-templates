package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.concurrent.Callable;

import javax.inject.Inject;


public class ProjectViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's
        // view model scope.
        creators.put(LiveDataMainEntityListViewModel.class, () -> viewModelSubComponent.projectViewModel());
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
