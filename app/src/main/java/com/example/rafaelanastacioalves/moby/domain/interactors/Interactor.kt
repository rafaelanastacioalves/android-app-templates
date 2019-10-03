package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.LiveData

interface Interactor<R : Interactor_temp.RequestValues> {

    fun <T : LiveData<*>> execute(requestValue: R): T

    interface RequestValues
}
