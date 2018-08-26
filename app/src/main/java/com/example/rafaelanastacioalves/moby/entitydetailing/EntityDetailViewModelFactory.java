package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailaingInteractor;
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor;
import com.example.rafaelanastacioalves.moby.entitymainlisting.LiveDataMainEntityListViewModel;


class EntityDetailViewModelFactory implements ViewModelProvider.Factory {

    private final EntityDetailaingInteractor mInteractor;

    public EntityDetailViewModelFactory(EntityDetailaingInteractor interactor){
        this.mInteractor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LiveDataMainEntityListViewModel.class)){
            return (T) new LiveDataEntityDetailsViewModel(mInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
