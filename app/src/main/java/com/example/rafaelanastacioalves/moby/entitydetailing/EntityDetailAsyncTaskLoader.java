package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rafaelanastacioalves.moby.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;


public class EntityDetailAsyncTaskLoader extends AsyncTaskLoader<EntityDetails> {
    private static String tripPackageId;
    private static EntityDetails entityDetails;

    public EntityDetailAsyncTaskLoader(Context context, String mTripPackageId) {
        super(context);
        this.tripPackageId = mTripPackageId;
    }

    @Override
    protected void onStartLoading() {
        if (tripPackageId != null && entityDetails !=null){
            deliverResult(entityDetails);
        }else {
            forceLoad();
        }

    }

    @Override
    public EntityDetails loadInBackground() {
        APIClient APIClient = ServiceGenerator.createService(APIClient.class);


        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here");
            return null;
        }
        Call<EntityDetails> call = APIClient.getTripPackageDetails(tripPackageId);


        try {
            Response<EntityDetails> response = call.execute();

            if (response.isSuccessful()) {
                Timber.i("response Successful");
                entityDetails = response.body();

                return entityDetails;
            } else {
                //TODO add more error management
                Timber.e(response.message());
                return null;
            }

        } catch (IOException e) {
            //TODO add more error management
            e.printStackTrace();
        }

        return entityDetails;
    }
}
