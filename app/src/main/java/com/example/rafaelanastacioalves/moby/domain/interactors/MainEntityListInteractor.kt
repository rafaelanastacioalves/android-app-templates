package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations

import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository

class MainEntityListInteractor :
        Interactor<LiveData<Resource<List<MainEntity>>>, MainEntityListInteractor.RequestValues> {

    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override fun execute(requestValues: RequestValues?): LiveData<Resource<List<MainEntity>>> {
        val repositoryLiveData = appRepository.mainEntity() as MutableLiveData<Resource<List<MainEntity>>>
        // aqui podemos aplicar transformações baseadas em regras de negócio usando
        // Transformations. Ex.: Transformations.map()
        Transformations.map<Resource<List<MainEntity>>, Any>(repositoryLiveData) { resource ->

            when (resource) {
                Resource.Status.GENERIC_ERROR -> resource.message = "Main entity list error"
            }


        }
        return repositoryLiveData
    }

    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
