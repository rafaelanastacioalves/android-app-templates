package com.example.rafaelanastacioalves.moby.retrofit;

import com.example.rafaelanastacioalves.moby.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.entities.MainEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AppRepository {

    public static Observable<List<MainEntity>> getMainEntityList(){
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

    return finalResult;
    }

    public static Observable<EntityDetails> getEntitiDetail(String tripPackageId) {

        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        return APIClient.getTripPackageDetails(tripPackageId);

    }
}
