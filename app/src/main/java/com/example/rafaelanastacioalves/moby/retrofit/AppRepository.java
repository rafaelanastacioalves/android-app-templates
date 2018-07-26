package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class AppRepository {

    public MutableLiveData<List<MainEntity>> getMainEntityList() {
        Timber.i("LiveDataMainEntityListViewModel loadData");
        MutableLiveData<List<MainEntity>> mainEntityList = new MutableLiveData<>();

        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Call<List<MainEntity>> call = APIClient.getTripPackageList();
        call.enqueue(new Callback<List<MainEntity>>() {
            @Override
            public void onResponse(Call<List<MainEntity>> call, Response<List<MainEntity>> response) {
                if (response.isSuccessful()) {
                    Timber.i("response Successful");
                    mainEntityList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MainEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mainEntityList;
    }
}
