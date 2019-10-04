package com.example.rafaelanastacioalves.moby.entitydetailing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import com.example.rafaelanastacioalves.moby.retrofit.APIClient
import com.example.rafaelanastacioalves.moby.retrofit.ServiceGenerator

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal class LiveDataEntityDetailsViewModel : ViewModel() {

    val entityDetails = MutableLiveData<EntityDetails>()

    val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()

    fun loadData(tripPackageId: String?) : LiveData<Resource<EntityDetails>> {
        Timber.i("LiveDataEntityDetailsViewModel loadData")

        return entityDetailsInteractor.execute(tripPackageId?.let{EntityDetailsInteractor.RequestValues(it)})
    }
}

