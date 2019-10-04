package com.example.rafaelanastacioalves.moby.retrofit;


import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface APIClient {


    @POST("/trip-packages")
    suspend fun getTripPackageList(): List<MainEntity>;

    @POST("/trip-packages/{tripPackageID}")
    suspend fun getTripPackageDetails(@Path("tripPackageID") id: String): EntityDetails

}
