package com.example.rafaelanastacioalves.moby.entities

import android.support.annotation.Nullable

class Resource<T> constructor(
        status: Status?,
        data: T?,
        message: String?) {

    companion object Factory {

        fun <T> success(successData: T?): Resource<T>  {
            return Resource(Status.SUCCESS,successData, null);
        }

        fun <T> error(status: Status, data: T?, msg: String): Resource<T> {
            val resource = Resource(status, data, msg)
            return resource
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }

    enum class Status { SUCCESS, INTERNAL_SERVER_ERROR ,GENERIC_ERROR, LOADING}
}