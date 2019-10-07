package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.LiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class AppRepository {

    public LiveData<Resource<List<MainEntity>>> getMainEntityList() {
        final APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected Call<List<MainEntity>> createCall() {
                return apiClient.getTripPackageList();
            }
        }.asLiveData();
    }

    public LiveData<Resource<EntityDetails>> getEntityDetails(final String id) {
        final APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundResource<EntityDetails, EntityDetails>() {
            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected Call<EntityDetails> createCall() {
                return apiClient.getTripPackageDetails(id);
            }
        }.asLiveData();
    }
}



