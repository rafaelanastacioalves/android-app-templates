package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainEntityListInteractor :
        Interactor<LiveData<Resource<List<MainEntity>>>, MainEntityListInteractor.RequestValues> {

    val appRepository: AppRepository


    init {
        appRepository = AppRepository
    }

    override fun execute(requestValues: RequestValues?): LiveData<Resource<List<MainEntity>>> {

        val liveData: LiveData<Resource<List<MainEntity>>> = liveData {
            emit(Resource.loading())
            var result : Resource<List<MainEntity>> = appRepository.mainEntity()
            emit(result)
        }
        return liveData





    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
