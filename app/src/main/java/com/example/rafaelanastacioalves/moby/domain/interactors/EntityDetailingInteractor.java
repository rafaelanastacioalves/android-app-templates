package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import timber.log.Timber;

public class EntityDetailingInteractor implements Interactor<EntityDetailingInteractor.RequestValues> {

    private final AppRepository appRepository;

    @Inject
    EntityDetailingInteractor(AppRepository appRepository){
        this.appRepository = appRepository;
    }

    @Override
    public MutableLiveData<EntityDetails> execute(RequestValues requestValues) {
        final MutableLiveData<EntityDetails> entityDetails = new MutableLiveData<>();
        Single<EntityDetails> repositorySingleRequest = appRepository.getEntityDetails(requestValues.resquestId);
        Timber.i("LiveDataEntityDetailsViewModel loadData");


        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Call<EntityDetails> call = APIClient.getTripPackageDetails(requestValues.getResquestId());
        repositorySingleRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> entityDetails.postValue(response));


        return entityDetails;

    }

    public static final class RequestValues implements Interactor.RequestValues {
        private final String resquestId;

        public RequestValues(String resquestId) {
            this.resquestId = resquestId;
        }


        public String getResquestId() {
            return resquestId;
        }
    }


}
