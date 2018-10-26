package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.LiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class AppRepository {

    public LiveData<Resource<List<MainEntity>>> getMainEntityList() {
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundSource<List<MainEntity>, List<MainEntity>>() {
            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected Call<List<MainEntity>> createCall() {
                return apiClient.getTripPackageList();
            }
        }.asLiveData();
    }

    public Single<EntityDetails> getEntityDetails(String id) {
        return Single.create(emitter -> {
            APIClient apiClient = ServiceGenerator.createService(APIClient.class);
            Call<EntityDetails> call = apiClient.getTripPackageDetails(id);
            call.enqueue(new Callback<EntityDetails>() {
                @Override
                public void onResponse(Call<EntityDetails> call, Response<EntityDetails> response) {
                    if (response.isSuccessful()) {
                        Timber.i("response Successful");
                        emitter.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<EntityDetails> call, Throwable t) {
                    emitter.onError(t);
                }
            });

        });

    }
}



