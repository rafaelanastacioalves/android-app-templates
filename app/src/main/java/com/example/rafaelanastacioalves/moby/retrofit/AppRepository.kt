package com.example.rafaelanastacioalves.moby.retrofit

import android.arch.lifecycle.LiveData
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

object AppRepository {

    public fun mainEntity(): LiveData<Resource<List<MainEntity>>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun makeCall(): List<MainEntity>? {

                var apiClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
                return apiClient.getTripPackageList()
            }

        }.asLiveData()
    }

}