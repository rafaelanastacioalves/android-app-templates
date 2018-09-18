package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class AppRepository {

    public Single<List<MainEntity>> getMainEntityList() {
        return Single.create(emitter -> {
            APIClient apiClient = ServiceGenerator.createService(APIClient.class);
            Call<List<MainEntity>> call = apiClient.getTripPackageList();
            call.enqueue(new Callback<List<MainEntity>>() {
                @Override
                public void onResponse(Call<List<MainEntity>> call, Response<List<MainEntity>> response) {
                    if (response.isSuccessful()) {
                        Timber.i("response Successful");
                        emitter.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<MainEntity>> call, Throwable t) {
                    emitter.onError(t);
                }
            });

        });

    }

    public Single<EntityDetails> getEntityDetails(String id){
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


