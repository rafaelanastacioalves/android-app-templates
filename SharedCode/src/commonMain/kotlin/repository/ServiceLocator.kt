package domain

import io.ktor.client.engine.HttpClientEngine
import repository.APIClient
import kotlin.native.concurrent.ThreadLocal

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
@ThreadLocal
object ServiceLocator {

    val moviesApi by lazy { APIClient(PlatformServiceLocator.httpClientEngine) }

}


/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}