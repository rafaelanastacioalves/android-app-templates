package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LiveDataMainEntityListViewModel extends ViewModel {

    private AppRepository githubRepository;
    private MutableLiveData<List<MainEntity>> mMainEntityList;


    public LiveDataMainEntityListViewModel(AppRepository appRepository){
        this.githubRepository = appRepository;
    }

    public MutableLiveData<List<MainEntity>> getMainEntityList() {

        mMainEntityList = githubRepository.getMainEntityList();
        return mMainEntityList;
    }


    public void loadData() {

    }
}
