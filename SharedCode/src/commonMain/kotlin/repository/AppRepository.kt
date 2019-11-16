package com.example.rafaelanastacioalves.moby.retrofit

import domain.ServiceLocator
import domain.domain.entities.EntityDetails
import domain.domain.entities.MainEntity
import domain.domain.entities.Resource
import repository.APIClient


object AppRepository {

    suspend fun mainEntity(): Resource<List<MainEntity>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun makeCall(): List<MainEntity>? {

                var apiClient: APIClient = ServiceLocator.moviesApi
                return apiClient.getTripPackageList()
            }

        }.fromHttpOnly()
    }

    suspend fun mainEntityAdditional(): Resource<List<MainEntity>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun makeCall(): List<MainEntity>? {

                var apiClient: APIClient = ServiceLocator.moviesApi
                return apiClient.getTripPackageListAdditional()
            }

        }.fromHttpOnly()
    }

    suspend fun entityDetails(requestId: String) : Resource<EntityDetails> {
        return object : NetworkBoundResource<EntityDetails, EntityDetails>(){
            override suspend fun makeCall(): EntityDetails? {
                var apiClient: APIClient = ServiceLocator.moviesApi
                return apiClient.getTripPackageDetails(requestId)
            }
        }.fromHttpOnly()
    }
}