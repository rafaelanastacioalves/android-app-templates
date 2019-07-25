package com.example.rafaelanastacioalves.moby.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static android.arch.lifecycle.Transformations.switchMap;

@Singleton
public class AppRepository {

    public LiveData<PagedList<MainEntity>> getMainEntityList() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();

        LivePagedListBuilder<String, MainEntity> mLivePagedListBuilder =  new LivePagedListBuilder<>(dataSourceFactory, 5);

        return mLivePagedListBuilder
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build();
    }

    public LiveData<Resource<EntityDetails>> getEntityDetails(String id) {
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundSource<EntityDetails, EntityDetails>() {
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



