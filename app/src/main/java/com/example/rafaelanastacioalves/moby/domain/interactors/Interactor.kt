package com.example.rafaelanastacioalves.moby.domain.interactors

import domain.domain.uiDispatcher
import kotlinx.coroutines.*

abstract class Interactor<out T ,in R : Interactor.RequestValues> {

    abstract suspend fun run(requestValue: R?): T


    suspend operator fun invoke(
            params: R?,
            onResult: (T) -> Unit = {}
    ){
        coroutineScope {
            val backGroundJob = async { run(params) }
            launch (uiDispatcher) {
                onResult(backGroundJob.await())
            }

        }
    }

    interface RequestValues
}
