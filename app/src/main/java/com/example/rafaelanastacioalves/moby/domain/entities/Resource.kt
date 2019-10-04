package com.example.rafaelanastacioalves.moby.domain.entities

class Resource<T> constructor(
        status: Status,
        val data: T?,
        var message: String?) {

    companion object Factory {

        fun <T> success(successData: T?): Resource<T>  {
            return Resource(Status.SUCCESS,successData, null);
        }

        fun <T> error(status: Status, data: T?, msg: String?): Resource<T> {
            return Resource(status, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }

    enum class Status { SUCCESS, INTERNAL_SERVER_ERROR ,GENERIC_ERROR, LOADING}
}