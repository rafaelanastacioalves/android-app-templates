package com.example.rafaelanastacioalves.moby.entitydetailing


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.EntityDetailsInteractor
import timber.log.Timber


internal class LiveDataEntityDetailsViewModel : ViewModel() {

    val entityDetails = MutableLiveData<Resource<EntityDetails>>()

    val entityDetailsInteractor: EntityDetailsInteractor = EntityDetailsInteractor()

    fun loadData(tripPackageId: String?) : MutableLiveData<Resource<EntityDetails>> {
        Timber.i("LiveDataEntityDetailsViewModel loadData")

        entityDetails.postValue(Resource.loading())
        entityDetailsInteractor.execute(viewModelScope,
                tripPackageId?.let{EntityDetailsInteractor.RequestValues(it)},{it -> handle(it)})
        return entityDetails
    }

    private fun handle(it: Resource<EntityDetails>?) {
        entityDetails.postValue(it)
    }


}

