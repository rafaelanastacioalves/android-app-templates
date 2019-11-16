package com.example.rafaelanastacioalves.moby.entitymainlisting;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import domain.domain.entities.MainEntity
import domain.domain.entities.Resource
import kotlinx.coroutines.launch


class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<Resource<List<MainEntity>>>();

    val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()



    fun loadData() : MutableLiveData<Resource<List<MainEntity>>> {
            mainEntityListInteractor.invoke(null, {
                handle(it)
            })


        return mainEntityList

    }

    private fun handle(it: Resource<List<MainEntity>>) {
        mainEntityList.postValue(it)
    }

}
