package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import com.example.rafaelanastacioalves.moby.base.BaseViewModel

import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.retrofit.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import javax.inject.Inject

class LiveDataMainEntityListViewModel : BaseViewModel() {

    @Inject
    lateinit var apiClient: APIClient

    val mainEntityList = MutableLiveData<List<MainEntity>>()

    init {
        loadData()
    }
    fun loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if(mainEntityList.getValue() != null){
            return;
        }

        var call: Call<List<MainEntity>> = apiClient.getTripPackageList();
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
