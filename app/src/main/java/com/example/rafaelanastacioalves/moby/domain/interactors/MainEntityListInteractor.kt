package com.example.rafaelanastacioalves.moby.domain.interactors

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainEntityListInteractor :
        Interactor<Resource<List<MainEntity>>, MainEntityListInteractor.RequestValues>() {

    val appRepository: AppRepository


    init {
        appRepository = AppRepository
    }

    override suspend fun run(requestValues: RequestValues?): Resource<List<MainEntity>> {
        var finalList: List<MainEntity> = ArrayList<MainEntity>()

        withContext(Dispatchers.IO) {

            // in this examaple we could call sequentially or wait for one result so we get some data to make another call, just saying...
            val deferredOne = async { appRepository.mainEntity() }
            val deferredTwo = async { appRepository.mainEntityAdditional() }

            var resultOne: List<MainEntity>? = deferredOne.await().data
            var resultTwo: List<MainEntity>? = deferredTwo.await().data

            resultOne?.let { finalList = finalList.union(resultOne).toList() }
            resultTwo?.let { finalList = finalList.union(resultTwo).toList() }

        }

        val result = Resource.success(finalList)

        return result


    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
