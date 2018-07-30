package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private MutableLiveData<EntityDetails> mEntityDetails = new MutableLiveData<>();

    public MutableLiveData<EntityDetails> getEntityDetails() {
        return mEntityDetails;
    }

    public void loadData(String tripPackageId) {
        Timber.i("LiveDataEntityDetailsViewModel loadData");

        if(mEntityDetails.getValue() != null){
            return;
        }


        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here");
            return;
        }

        Observable<EntityDetails> result = AppRepository.getEntitiDetail(tripPackageId);
        result.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mEntityDetails.setValue(response),
                        throwable -> throwable.printStackTrace());



    }
}

