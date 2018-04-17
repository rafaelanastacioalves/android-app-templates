package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rafaelanastacioalves.moby.common.Utility;
import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;


public class MainEntityListAsyncTaskLoader extends AsyncTaskLoader<List<MainEntity>> {

    private static List<MainEntity> mainEntityList;

    public MainEntityListAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mainEntityList !=null){
            deliverResult(mainEntityList);
        }else{
            forceLoad();
        }
    }

    @Override
    public List<MainEntity> loadInBackground() {
        Timber.i("ReposAsyncTaskLoader loadInBackground");

        mainEntityList = null;
        APIClient APIClient = ServiceGenerator.createService(APIClient.class);

        Call<List<MainEntity>> call = APIClient.getTripPackageList();
        try {
            Response<List<MainEntity>> response = call.execute();

            if (response.isSuccessful()) {
                Timber.i("response Successful");
                mainEntityList = response.body();

            }
        } catch (IOException e) {
            //TODO add error management here
            e.printStackTrace();
        }


        return mainEntityList;
    }
}
