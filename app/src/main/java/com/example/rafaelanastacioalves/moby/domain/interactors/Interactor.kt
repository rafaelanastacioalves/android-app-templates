package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.LiveData

interface Interactor<out T ,in R : Interactor.RequestValues> {

    fun execute(requestValue: R?): T

    interface RequestValues
}
