package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor;


class MainEntityListingViewModelFactory implements ViewModelProvider.Factory {

    private final MainEntityListInteractor mInteractor;

    public MainEntityListingViewModelFactory(MainEntityListInteractor interactor){
        this.mInteractor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LiveDataMainEntityListViewModel.class)){
            return (T) new LiveDataMainEntityListViewModel(mInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
