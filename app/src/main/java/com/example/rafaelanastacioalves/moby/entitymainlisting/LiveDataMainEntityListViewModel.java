package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

class LiveDataMainEntityListViewModel extends ViewModel {

    private MutableLiveData<List<MainEntity>> mMainEntityList = new MutableLiveData<>();

    public MutableLiveData<List<MainEntity>> getMainEntityList() {
        return mMainEntityList;
    }


    public void loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if(mMainEntityList.getValue() != null){
            return;
        }

        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Call<List<MainEntity>> call = APIClient.getTripPackageList();
        call.enqueue(new Callback<List<MainEntity>>() {
            @Override
            public void onResponse(Call<List<MainEntity>> call, Response<List<MainEntity>> response) {
                if (response.isSuccessful()) {
                    Timber.i("response Successful");
                    mMainEntityList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MainEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
