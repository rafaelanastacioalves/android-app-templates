package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rafaelanastacioalves.moby.entities.MainEntity
import com.example.rafaelanastacioalves.moby.entities.Resource
import com.example.rafaelanastacioalves.moby.retrofit.APIClient
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LiveDataMainEntityListViewModel : ViewModel() {

    val mainEntityList = MutableLiveData<Resource<List<MainEntity>>>()

    val viewModelScope = CoroutineScope(Dispatchers.Main);

    fun loadData() {

        Timber.i("LiveDataMainEntityListViewModel loadData");

        if (mainEntityList.getValue() != null) {
            return;
        }

        var APIClient: APIClient = ServiceGenerator.createService(APIClient::class.java);
        var call: Call<List<MainEntity>> = APIClient.getTripPackageList();

        viewModelScope.launch(Dispatchers.IO) {
            mainEntityList.postValue(call.await());
        }

    }


    suspend fun convertToSuspend(call: Call<List<MainEntity>>): List<MainEntity>? {
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<List<MainEntity>> {

                override fun onResponse(call: Call<List<MainEntity>>, response: Response<List<MainEntity>>) {
                    if (response.isSuccessful()) {
                        Timber.i("response Successful");
                        continuation.resume(response.body());
                    }
                }

                override fun onFailure(call: Call<List<MainEntity>>, t: Throwable) {
                    continuation.resumeWithException(t);
                }
            })
        }
    }

    suspend fun <T> Call<T>.await(): Resource<T> {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T?>) {
                    if (response.isSuccessful()) {
                        Timber.i("response Successful");
                        continuation.resume(
                                Resource.success(response.body())
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resume(
                            Resource.error(Resource.Status.GENERIC_ERROR, null, t.toString())
                    )
                }
            })
        }
    }
}
