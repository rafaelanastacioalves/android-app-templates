package com.example.rafaelanastacioalves.moby.trippackagelisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rafaelanastacioalves.moby.common.Utility;
import com.example.rafaelanastacioalves.moby.entities.DeviceSpecs;
import com.example.rafaelanastacioalves.moby.entities.TripPackage;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;
import com.example.rafaelanastacioalves.moby.retrofit.TripAPIClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;


public class TripPackageListAsyncTaskLoader extends AsyncTaskLoader<List<TripPackage>> {

    private static List<TripPackage> tripPackageList;

    public TripPackageListAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (tripPackageList!=null){
            deliverResult(tripPackageList);
        }else{
            forceLoad();
        }
    }

    @Override
    public List<TripPackage> loadInBackground() {
        Timber.i("ReposAsyncTaskLoader loadInBackground");

        tripPackageList = null;
        TripAPIClient tripAPIClient = ServiceGenerator.createService(TripAPIClient.class);

        DeviceSpecs deviceSpecs = new DeviceSpecs(
                Utility.getDeviceBrand(),
                Utility.getDeviceModel(),
                Utility.getAndroidVersion()
        );

        Call<List<TripPackage>> call = tripAPIClient.getTripPackageList(deviceSpecs);
        try {
            Response<List<TripPackage>> response = call.execute();

            if (response.isSuccessful()) {
                Timber.i("response Successful");
                tripPackageList = response.body();

            }
        } catch (IOException e) {
            //TODO add error management here
            e.printStackTrace();
        }


        return tripPackageList;
    }
}
