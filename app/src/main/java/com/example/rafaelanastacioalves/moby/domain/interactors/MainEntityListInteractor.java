package com.example.rafaelanastacioalves.moby.domain.interactors;

import android.arch.lifecycle.MutableLiveData;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainEntityListInteractor implements Interactor<MainEntityListInteractor.RequestValues>{

    private final AppRepository appRepository;

    @Inject
    MainEntityListInteractor(AppRepository appRepository){
        this.appRepository = appRepository;
    }
    @Override
    public MutableLiveData<List<MainEntity>> execute(RequestValues requestValues) {
        final MutableLiveData<List<MainEntity>> mainEntityList = new MutableLiveData<>();
        Single<List<MainEntity>> repositorySingleRequest = appRepository.getMainEntityList();
        repositorySingleRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mainEntityList.postValue(response));
        return mainEntityList;
    }




    public static class RequestValues implements Interactor.RequestValues {
        // in this case we don't need nothing for this use case
    }
}
