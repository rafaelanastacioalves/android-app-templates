package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
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

        viewModelScope.launch(Dispatchers.IO) {
            mainEntityList.postValue()

        }

    }




}
