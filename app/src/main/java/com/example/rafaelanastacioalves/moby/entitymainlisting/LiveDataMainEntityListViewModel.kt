package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveDataMainEntityListViewModel : ViewModel() {

    lateinit var mainEntityList: LiveData<Resource<List<MainEntity>>>

    val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()

    val viewModelScope = CoroutineScope(Dispatchers.Default);



    fun loadData() : LiveData<Resource<List<MainEntity>>> {
        mainEntityList = mainEntityListInteractor.execute(null)


        return mainEntityList

    }




}
