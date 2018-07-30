package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private MutableLiveData<EntityDetails> mEntityDetails = new MutableLiveData<>();

    public MutableLiveData<EntityDetails> getEntityDetails() {
        return mEntityDetails;
    }

    public void loadData(String tripPackageId) {
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

