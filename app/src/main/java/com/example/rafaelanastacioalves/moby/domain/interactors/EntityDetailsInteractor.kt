package com.example.rafaelanastacioalves.moby.domain.interactors


import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.AppRepository

class EntityDetailsInteractor :
        Interactor<Resource<EntityDetails>?, EntityDetailsInteractor.RequestValues>() {
    val appRepository: AppRepository

    init {
        appRepository = AppRepository
    }

    override suspend fun run(requestValue: EntityDetailsInteractor.RequestValues?): Resource<EntityDetails>? {
        var result = requestValue?.requestId?.let { appRepository.entityDetails(it) }
        return result
    }

    class RequestValues(val requestId: String) : Interactor.RequestValues

}
