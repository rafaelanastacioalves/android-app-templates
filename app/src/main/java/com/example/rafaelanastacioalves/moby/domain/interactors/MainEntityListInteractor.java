package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import javax.inject.Inject;

public class MainEntityListInteractor implements Interactor<MainEntityListInteractor.RequestValues>{

    private final AppRepository appRepository;

    @Inject
    MainEntityListInteractor(AppRepository appRepository){
        this.appRepository = appRepository;
    }
    @Override
    public LiveData<PagedList<MainEntity>> execute(RequestValues requestValues) {
        LiveData<PagedList<MainEntity>> repositoryLiveData = appRepository.getMainEntityList();
        // aqui podemos aplicar transformações baseadas em regras de negócio usando
        // Transformations. Ex.: Transformations.map()
        return repositoryLiveData ;
    }

    public static class RequestValues implements Interactor.RequestValues {
        // in this case we don't need nothing for this use case
    }
}
