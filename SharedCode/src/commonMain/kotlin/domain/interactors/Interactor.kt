package com.example.rafaelanastacioalves.moby.domain.interactors

import domain.domain.defaultDispatcher
import domain.domain.uiDispatcher
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//TODO - create proper constructor as in example
abstract class Interactor<out T ,in R : Interactor.RequestValues> {

    protected lateinit var scope: PresenterCoroutineScope

    abstract suspend fun run(requestValue: R?): T


    operator fun invoke(
            params: R?,
            onResult: (T) -> Unit = {}
    ){

        scope = PresenterCoroutineScope(defaultDispatcher)
        scope.launch {
            val backGroundJob = async { run(params) }
                onResult(backGroundJob.await())
        }
    }

    interface RequestValues
}

class PresenterCoroutineScope(
        context: CoroutineContext
) : CoroutineScope {

    private var onViewDetachJob = Job()
    override val coroutineContext: CoroutineContext = context + onViewDetachJob

    fun viewDetached() {
        onViewDetachJob.cancel()
    }
}