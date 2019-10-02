package com.example.rafaelanastacioalves.moby.retrofit

import android.arch.lifecycle.MutableLiveData
import com.example.rafaelanastacioalves.moby.entities.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection

abstract class NetworkBoundResource<ResultType, RequestType> {

    val result: MutableLiveData<Resource<ResultType>> = MutableLiveData()
    val viewModelScope = CoroutineScope(Dispatchers.IO);


    init {
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    fun fetchFromNetwork() {

        var resultData: ResultType?
        viewModelScope.launch {
            try {
                resultData = makeCall()

                result.value = Resource(Resource.Status.SUCCESS, resultData, null)

            } catch (exception: Exception) {
                if (exception is HttpException) {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                            result.value = Resource.error(
                                    Resource.Status.INTERNAL_SERVER_ERROR
                                    , null
                                    , null)
                        }

                        else -> {
                            result.value = Resource.error(
                                    Resource.Status.GENERIC_ERROR,
                                    null,
                                    null
                                    )
                        }

                    }
                }
            }
        }
    }

    abstract fun makeCall(): ResultType?

}