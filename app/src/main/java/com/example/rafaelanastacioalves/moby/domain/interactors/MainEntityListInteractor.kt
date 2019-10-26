package com.example.rafaelanastacioalves.moby.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository
import kotlinx.coroutines.*

class MainEntityListInteractor :
        Interactor<LiveData<Resource<List<MainEntity>>>, MainEntityListInteractor.RequestValues> {

    val appRepository: AppRepository


    init {
        appRepository = AppRepository
    }

    override fun execute(requestValues: RequestValues?): LiveData<Resource<List<MainEntity>>> {

        val liveData: LiveData<Resource<List<MainEntity>>> = liveData {
            withContext(Dispatchers.IO) {
                emit(Resource.loading())

                // in this examaple we could call sequentially or wait for one result so we get some data to make another call, just saying...
                val deferredOne = async {appRepository.mainEntity()}
                val deferredTwo = async { appRepository.mainEntityAdditional() }

                var resultOne : List<MainEntity>?  = deferredOne.await().data
                var resultTwo : List<MainEntity>?  = deferredTwo.await().data

                var finalList: List<MainEntity> = ArrayList<MainEntity>()
                resultOne?.let { finalList = finalList.union(resultOne).toList() }
                resultTwo?.let { finalList = finalList.union(resultTwo).toList()}

                val result = Resource.success(finalList)
                emit(result)
            }

        }
        return liveData





    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
