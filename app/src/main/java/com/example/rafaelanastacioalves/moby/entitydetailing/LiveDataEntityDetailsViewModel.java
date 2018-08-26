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

    private final EntityDetailaingInteractor mEntityDetailInteractor;
    private MutableLiveData<EntityDetails> mEntityDetails = new MutableLiveData<>();

    public LiveDataEntityDetailsViewModel(EntityDetailaingInteractor mInteractor) {
        this.mEntityDetailInteractor = mInteractor
    }

    public MutableLiveData<EntityDetails> getEntityDetails() {

        mEntityDetails = mEntityDetailInteractor.execute();
        return mEntityDetails;
    }

    public void loadData(String tripPackageId) {

    }
}

