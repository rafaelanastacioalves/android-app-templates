package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.NetworkBoundSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class MainEntityListInteractor implements Interactor<MainEntityListInteractor.RequestValues>{

    private final AppRepository appRepository;

    @Inject
    MainEntityListInteractor(AppRepository appRepository){
        this.appRepository = appRepository;
    }
    @Override
    public LiveData<Resource<List<MainEntity>>> execute(RequestValues requestValues) {
        LiveData<Resource<List<MainEntity>>> repositoryLiveData = appRepository.getMainEntityList();
        // aqui podemos aplicar transformações baseadas em regras de negócio usando
        // Transformations. Ex.: Transformations.map()
        return repositoryLiveData ;
    }



    public static class RequestValues implements Interactor.RequestValues {
        // in this case we don't need nothing for this use case
    }
}
