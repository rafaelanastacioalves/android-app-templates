package com.example.rafaelanastacioalves.moby.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.net.HttpURLConnection

abstract class NetworkBoundResource<ResultType, RequestType> {

    val viewModelScope = CoroutineScope(Dispatchers.IO);
    lateinit var result: Resource<ResultType>

    suspend fun fetchFromNetwork() {

        var resultData: ResultType?
            try {
                resultData = makeCall()

                result = Resource(Resource.Status.SUCCESS, resultData, null)

            } catch (exception: Exception) {
                if (exception is HttpException) {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                            result = Resource.error(
                                    Resource.Status.INTERNAL_SERVER_ERROR
                                    , null
                                    , null)
                        }

                        else -> {
                            result = Resource.error(
                                    Resource.Status.GENERIC_ERROR,
                                    null,
                                    null
                            )
                        }

                    }
                }else{
                    result = Resource.error(Resource.Status.GENERIC_ERROR,
                            null,
                            null)
                }
            }

    }

    abstract suspend fun makeCall(): ResultType?

    suspend fun fromHttpOnly(): Resource<ResultType> {
        fetchFromNetwork()

        return result
    }

    fun asLiveData(): LiveData<Resource<RequestType>> {
        return MutableLiveData()
    }

}