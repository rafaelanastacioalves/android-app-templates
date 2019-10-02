package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rafaelanastacioalves.moby.entities.MainEntity
import com.example.rafaelanastacioalves.moby.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.APIClient
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<List<MainEntity>>()

    val viewModelScope = CoroutineScope(Dispatchers.Main);

    suspend fun loadData() {

        Timber.i("LiveDataMainEntityListViewModel loadData");

        if (mainEntityList.getValue() != null) {
            return;
        }

        var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
        viewModelScope.launch(Dispatchers.IO) {
            mainEntityList.postValue(apiClient.getTripPackageList())

        }

    }




}
