package com.example.rafaelanastacioalves.moby.retrofit

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

object AppRepository {

    suspend fun mainEntity(): Resource<List<MainEntity>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun makeCall(): List<MainEntity>? {

                var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
                return apiClient.getTripPackageList()
            }

        }.fromHttpOnly()
    }

    suspend fun mainEntityAdditional(): Resource<List<MainEntity>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun makeCall(): List<MainEntity>? {

                var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
                return apiClient.getTripPackageListAdditional()
            }

        }.fromHttpOnly()
    }

    suspend fun entityDetails(requestId: String) : Resource<EntityDetails> {
        return object : NetworkBoundResource<EntityDetails, EntityDetails>(){
            override suspend fun makeCall(): EntityDetails? {
                var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java)
                return apiClient.getTripPackageDetails(requestId)
            }
        }.fromHttpOnly()
    }
}