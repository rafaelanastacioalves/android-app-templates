package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailingInteractor;


public class LiveDataEntityDetailsViewModel extends ViewModel {

    private EntityDetailingInteractor mEntityDetailInteractor;
    private MutableLiveData<EntityDetails> mEntityDetails;

    public LiveDataEntityDetailsViewModel(EntityDetailingInteractor mInteractor) {
        this.mEntityDetailInteractor = mInteractor;
    }


    public MutableLiveData<EntityDetails> getEntityDetails(String tripPackageId) {
        EntityDetailingInteractor.RequestValues requestValues = new EntityDetailingInteractor.RequestValues(tripPackageId);
        mEntityDetails = mEntityDetailInteractor.execute(requestValues);
        return mEntityDetails;
    }


}

