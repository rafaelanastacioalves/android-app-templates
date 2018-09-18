package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailingInteractor;


class EntityDetailViewModelFactory implements ViewModelProvider.Factory {

    private final EntityDetailingInteractor mInteractor;

    public EntityDetailViewModelFactory(EntityDetailingInteractor interactor){
        this.mInteractor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LiveDataEntityDetailsViewModel.class)){
            return (T) new LiveDataEntityDetailsViewModel(mInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
