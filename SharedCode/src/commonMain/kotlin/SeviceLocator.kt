package domain

import kotlin.native.concurrent.ThreadLocal
import io.ktor.client.engine.HttpClientEngine


@ThreadLocal
object SeviceLocator {


}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}