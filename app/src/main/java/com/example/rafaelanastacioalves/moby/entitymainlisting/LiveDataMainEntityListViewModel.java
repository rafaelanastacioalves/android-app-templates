package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor;

public class LiveDataMainEntityListViewModel extends ViewModel {

    private MainEntityListInteractor mMainEntityListinInteractor;
    private LiveData<PagedList<MainEntity>> mMainEntityList;


    public LiveDataMainEntityListViewModel(MainEntityListInteractor interactor){
        this.mMainEntityListinInteractor = interactor;
    }

    public LiveData<PagedList<MainEntity>> getMainEntityList() {
        mMainEntityList = mMainEntityListinInteractor.execute(null);
        return mMainEntityList;
    }

}
