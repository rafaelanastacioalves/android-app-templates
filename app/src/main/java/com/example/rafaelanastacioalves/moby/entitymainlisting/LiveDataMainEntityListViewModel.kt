package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<List<MainEntity>>()

    val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()

    val viewModelScope = CoroutineScope(Dispatchers.Main);

    fun loadData() : LiveData<Resource<List<MainEntity>>> {

        Timber.i("LiveDataMainEntityListViewModel loadData");

        return mainEntityListInteractor.execute(null)


    }




}
