package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;


 class ProjectViewModelFactory implements ViewModelProvider.Factory {

    private final AppRepository appRepository;

    public ProjectViewModelFactory(AppRepository appRepository){
        this.appRepository = appRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LiveDataMainEntityListViewModel.class)){
            return (T) new LiveDataMainEntityListViewModel(appRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
