package domain.domain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val defaultDispatcher: CoroutineContext
    get() = Dispatchers.Main

actual val uiDispatcher: CoroutineContext
    get() = Dispatchers.Default