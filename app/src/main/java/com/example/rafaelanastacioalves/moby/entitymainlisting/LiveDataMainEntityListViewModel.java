package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

class LiveDataMainEntityListViewModel extends ViewModel {

    private MutableLiveData<List<MainEntity>> mMainEntityList = new MutableLiveData<>();

    public MutableLiveData<List<MainEntity>> getMainEntityList() {
        return mMainEntityList;
    }


    public void loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if(mMainEntityList.getValue() != null){
            return;
        }
        

        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Observable<List<MainEntity>> finalResult = Observable.zip(
                APIClient.getTripPackageList().subscribeOn(Schedulers.io()),
                APIClient.getTripPackageListAdditional().subscribeOn(Schedulers.io()),
                (list1, list2) -> list1.addAll(list2)

        );

        finalResult.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mMainEntityList.setValue(response),
                        throwable -> throwable.printStackTrace());
    }
}
