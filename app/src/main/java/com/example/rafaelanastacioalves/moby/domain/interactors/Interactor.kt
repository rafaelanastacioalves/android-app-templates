package com.example.rafaelanastacioalves.moby.domain.interactors

import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class Interactor<out T ,in R : Interactor.RequestValues> {

    abstract suspend fun run(requestValue: R?): T

    open fun execute(
            scope: CoroutineScope,
            params: R?,
            onResult: (T) -> Unit = {}
    ){
        val backGroundJob = scope.async { run(params) }
        scope.launch { onResult(backGroundJob.await()) }
    }

    interface RequestValues
}
