package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        Observable<List<MainEntity>> callObservable = APIClient.getTripPackageList();
        callObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mMainEntityList.setValue(response),
                        throwable -> throwable.printStackTrace());
    }
}
