package domain

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
@ThreadLocal
actual object PlatformServiceLocator {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }
}