package com.example.rafaelanastacioalves.moby.domain.interactors



import com.example.rafaelanastacioalves.moby.retrofit.AppRepository
import domain.domain.entities.EntityDetails
import domain.domain.entities.Resource

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
