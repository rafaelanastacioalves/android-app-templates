package com.example.rafaelanastacioalves.moby.entitymainlisting;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor


class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<Resource<List<MainEntity>>>();

    val mainEntityListInteractor: MainEntityListInteractor = MainEntityListInteractor()



    fun loadData() : MutableLiveData<Resource<List<MainEntity>>> {
        mainEntityListInteractor.execute(viewModelScope,null, {
            handle(it)
        })

        return mainEntityList

    }

    private fun handle(it: Resource<List<MainEntity>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
