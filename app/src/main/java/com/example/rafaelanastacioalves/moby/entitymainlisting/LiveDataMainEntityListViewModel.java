package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.vo.MainEntity;
import com.example.rafaelanastacioalves.moby.repository.AppRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LiveDataMainEntityListViewModel extends ViewModel {

    private MutableLiveData<List<MainEntity>> mMainEntityList = new MutableLiveData<>();

    public MutableLiveData<List<MainEntity>> getMainEntityList() {
        return mMainEntityList;
    }


    public void loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if (mMainEntityList.getValue() != null) {
            return;
        }


        Observable<List<MainEntity>> finalResult = AppRepository.getMainEntityList();

        finalResult.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mMainEntityList.setValue(response),
                        throwable -> throwable.printStackTrace());
    }

}
