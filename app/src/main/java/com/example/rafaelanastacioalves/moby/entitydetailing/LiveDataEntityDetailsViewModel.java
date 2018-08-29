package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailaingInteractor;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private EntityDetailaingInteractor mEntityDetailInteractor;
    private MutableLiveData<EntityDetails> mEntityDetails;

    public LiveDataEntityDetailsViewModel(EntityDetailaingInteractor mInteractor) {
        this.mEntityDetailInteractor = mInteractor;
    }

    public MutableLiveData<EntityDetails> getEntityDetails(String tripPackageId) {
        EntityDetailaingInteractor.RequestValues requestValues = new EntityDetailaingInteractor.RequestValues(tripPackageId);
        mEntityDetails = mEntityDetailInteractor.execute(requestValues);
        return mEntityDetails;
    }


}

