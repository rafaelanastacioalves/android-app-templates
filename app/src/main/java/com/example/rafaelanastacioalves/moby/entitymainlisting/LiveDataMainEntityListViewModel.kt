package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<List<MainEntity>>()


    fun loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if(mainEntityList.getValue() != null){
            return;
        }

        var APIClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
        var call: Call<List<MainEntity>> = APIClient.getTripPackageList();
        call.enqueue(object : Callback<List<MainEntity>> {

            override fun onResponse(call: Call<List<MainEntity>>, response: Response<List<MainEntity>>) {
                if (response.isSuccessful()) {
                    Timber.i("response Successful");
                    mainEntityList.setValue(response.body());
                }
            }

            override fun onFailure(call: Call<List<MainEntity>>, t: Throwable) {
                t.printStackTrace();
            }
        })

    }
}
