package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor;

import java.util.List;

public class LiveDataMainEntityListViewModel extends ViewModel {

    private MainEntityListInteractor mMainEntityListinInteractor;
    private LiveData<Resource<List<MainEntity>>> mMainEntityList;


    public LiveDataMainEntityListViewModel(MainEntityListInteractor interactor){
        this.mMainEntityListinInteractor = interactor;
    }

    public LiveData<Resource<List<MainEntity>>> getMainEntityList() {
        mMainEntityList = mMainEntityListinInteractor.execute(null);
        return mMainEntityList;
    }

}
