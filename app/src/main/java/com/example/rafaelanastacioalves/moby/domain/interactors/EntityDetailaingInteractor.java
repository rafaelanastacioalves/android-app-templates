package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class EntityDetailaingInteractor implements Interactor {

    private final AppRepository appRepository;

    @Inject
    EntityDetailaingInteractor(AppRepository appRepository){
        this.appRepository = appRepository;
    }

    @Override
    public MutableLiveData<EntityDetails> execute() {
        final MutableLiveData<EntityDetails> entityDetails = new MutableLiveData<>();
        Single<EntityDetails> repositorySingleRequest = appRepository.getEntityDetails()
        Timber.i("LiveDataEntityDetailsViewModel loadData");

        if(mEntityDetails.getValue() != null){
            return;
        }


        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here");
            return;
        }
        Call<EntityDetails> call = APIClient.getTripPackageDetails(tripPackageId);


        call.enqueue(new Callback<EntityDetails>() {
            @Override
            public void onResponse(Call<EntityDetails> call, Response<EntityDetails> response) {
                if (response.isSuccessful()) {
                    Timber.i("response Successful");
                    mEntityDetails.postValue(response.body());

                } else {
                    Timber.e(response.message());
                }
            }

            @Override
            public void onFailure(Call<EntityDetails> call, Throwable t) {
                //TODO add more error management
                t.printStackTrace();
            }

        });



    }
}
