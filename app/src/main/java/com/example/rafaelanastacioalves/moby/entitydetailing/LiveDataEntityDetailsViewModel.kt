package com.example.rafaelanastacioalves.moby.entitydetailing

import android.arch.lifecycle.MutableLiveData
import com.example.rafaelanastacioalves.moby.base.BaseViewModel

import com.example.rafaelanastacioalves.moby.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.retrofit.APIClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class LiveDataEntityDetailsViewModel : BaseViewModel() {

    val entityDetails = MutableLiveData<EntityDetails>()

    @Inject
    lateinit var apiClient: APIClient


    fun loadData(tripPackageId: String?) {
        Timber.i("LiveDataEntityDetailsViewModel loadData")

        if (entityDetails.value != null) {
            return
        }


        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here")
            return
        }
        val call = apiClient.getTripPackageDetails(tripPackageId)


        call.enqueue(object : Callback<EntityDetails> {
            override fun onResponse(call: Call<EntityDetails>, response: Response<EntityDetails>) {
                if (response.isSuccessful) {
                    Timber.i("response Successful")
                    entityDetails.postValue(response.body())

                } else {
                    Timber.e(response.message())
                }
            }

            override fun onFailure(call: Call<EntityDetails>, t: Throwable) {
                //TODO add more error management
                t.printStackTrace()
            }

        })


    }
}

