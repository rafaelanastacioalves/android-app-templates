package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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


        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Observable<List<MainEntity>> finalResult = Observable.combineLatest(
                APIClient.getTripPackageList().subscribeOn(Schedulers.io()),
                APIClient.getTripPackageListAdditional().subscribeOn(Schedulers.io()),
                (l1, l2) -> {
                    List<MainEntity> finalList = new ArrayList<>();
                    finalList.addAll(l1);
                    finalList.addAll(l2);
                    return finalList;
                });

        finalResult.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mMainEntityList.setValue(response),
                        throwable -> throwable.printStackTrace());
    }

}
