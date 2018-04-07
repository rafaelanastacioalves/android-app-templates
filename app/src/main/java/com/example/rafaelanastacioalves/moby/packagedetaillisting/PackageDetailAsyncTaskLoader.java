package com.example.rafaelanastacioalves.moby.packagedetaillisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rafaelanastacioalves.moby.common.Utility;
import com.example.rafaelanastacioalves.moby.entities.DeviceSpecs;
import com.example.rafaelanastacioalves.moby.entities.TripPackageDetails;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;
import com.example.rafaelanastacioalves.moby.retrofit.TripAPIClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;


public class PackageDetailAsyncTaskLoader extends AsyncTaskLoader<TripPackageDetails> {
    private static String tripPackageId;
    private static TripPackageDetails tripPackageDetails;

    public PackageDetailAsyncTaskLoader(Context context, String mTripPackageId) {
        super(context);
        this.tripPackageId = mTripPackageId;
    }

    @Override
    protected void onStartLoading() {
        if (tripPackageId != null && tripPackageDetails !=null){
            deliverResult(tripPackageDetails);
        }else {
            forceLoad();
        }

    }

    @Override
    public TripPackageDetails loadInBackground() {
        TripAPIClient tripAPIClient = ServiceGenerator.createService(TripAPIClient.class);

        DeviceSpecs deviceSpecs = new DeviceSpecs(
                Utility.getDeviceBrand(),
                Utility.getDeviceModel(),
                Utility.getAndroidVersion()
        );
        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here");
            return null;
        }
        Call<TripPackageDetails> call = tripAPIClient.getTripPackageDetails(deviceSpecs, tripPackageId);


        try {
            Response<TripPackageDetails> response = call.execute();

            if (response.isSuccessful()) {
                Timber.i("response Successful");
                tripPackageDetails = response.body();

                return tripPackageDetails;
            } else {
                //TODO add more error management
                Timber.e(response.message());
                return null;
            }

        } catch (IOException e) {
            //TODO add more error management
            e.printStackTrace();
        }

        return tripPackageDetails;
    }
}
