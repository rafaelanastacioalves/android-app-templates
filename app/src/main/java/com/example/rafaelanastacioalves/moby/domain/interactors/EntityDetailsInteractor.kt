package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository

class EntityDetailsInteractor : Interactor<LiveData<Resource<EntityDetails>>, EntityDetailsInteractor.RequestValues> {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override fun execute(requestValue: EntityDetailsInteractor.RequestValues?): LiveData<Resource<EntityDetails>> {
        val repositoryLiveData = requestValue?.requestId?.let { appRepository.entityDetails(it) }
        return repositoryLiveData as MutableLiveData<Resource<EntityDetails>>
    }

    class RequestValues(val requestId: String) : Interactor.RequestValues

}
