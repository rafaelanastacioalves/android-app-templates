package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.util.List;

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
    public LiveData<Resource<EntityDetails>> execute(RequestValues requestValues) {
        LiveData<Resource<EntityDetails>> repositoryLiveData = appRepository.getEntityDetails(requestValues.resquestId);
        // aqui podemos aplicar transformações baseadas em regras de negócio usando
        // Transformations. Ex.: Transformations.map()
        return repositoryLiveData ;

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
