package com.example.rafaelanastacioalves.moby.entitydetailing

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.rafaelanastacioalves.moby.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.retrofit.APIClient
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator

import java.io.IOException

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal class LiveDataEntityDetailsViewModel : BaseViewModel() {

    val entityDetails = MutableLiveData<EntityDetails>()

    fun loadData(tripPackageId: String?) {
        Timber.i("LiveDataEntityDetailsViewModel loadData")

        if (entityDetails.value != null) {
            return
        }


        val APIClient = ServiceGenerator.createService(APIClient::class.java)
        if (tripPackageId == null) {
            Timber.w("loadInBackground - not supposed to have null variable here")
            return
        }
        val call = APIClient.getTripPackageDetails(tripPackageId)


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

