package com.example.rafaelanastacioalves.moby.retrofit


import domain.domain.entities.Resource
import domain.domain.uiDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class NetworkBoundResource<ResultType, RequestType> {

    val viewModelScope = CoroutineScope(uiDispatcher);
    lateinit var result: Resource<ResultType>

    suspend fun fetchFromNetwork() {

        var resultData: ResultType?
        try {
            resultData = makeCall()

            result = Resource(Resource.Status.SUCCESS, resultData, null)

        } catch (exception: Exception) {
//                if (exception is HttpException) {
//                    when (exception.code()) {
//                        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
//                            result = Resource.error(
//                                    Resource.Status.INTERNAL_SERVER_ERROR
//                                    , null
//                                    , null)
//                        }
//
//                        else -> {
//                            result = Resource.error(
//                                    Resource.Status.GENERIC_ERROR,
//                                    null,
//                                    null
//                            )
//                        }
//
//                    }
//                }else{
            result = Resource.error(Resource.Status.GENERIC_ERROR,
                    null,
                    exception.message)
//                }
//            }

        }
    }

    abstract suspend fun makeCall(): ResultType?

    suspend fun fromHttpOnly(): Resource<ResultType> {
        fetchFromNetwork()

        return result
    }



}