package repository

import domain.domain.entities.EntityDetails
import domain.domain.entities.MainEntity
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

private const val BASE_URL = "private-ff953-template25.apiary-mock.com"

@Suppress("EXPERIMENTAL_API_USAGE")
class APIClient(clientEngine: HttpClientEngine) {


    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getTripPackageList(): List<MainEntity>{
        // Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
        // JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
        // and we have to manually use PopularMoviesEntity.serializer()
        val response = client.post<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP
                host = BASE_URL
                encodedPath = "/trip-packages"
                body = ""
            }
        }

        val jsonBody = response.readText()
        return Json.parse(MainEntity.serializer().list, jsonBody)
    }

    suspend fun getTripPackageListAdditional(): List<MainEntity>{
        // Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
        // JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
        // and we have to manually use PopularMoviesEntity.serializer()
        val response = client.post<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP
                host = BASE_URL
                encodedPath = "/trip-packages-additional"
                body = ""
            }
        }

        val jsonBody = response.readText()
        return Json.parse(MainEntity.serializer().list, jsonBody)
    }

    suspend fun getTripPackageDetails(id: String): EntityDetails {
        // Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
        // JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
        // and we have to manually use PopularMoviesEntity.serializer()
        val response = client.post<HttpResponse>{
            url {
                protocol = URLProtocol.HTTP
                host = BASE_URL
                encodedPath = "/trip-packages/" + id
            }
        }
        val jsonBody = response.readText()
        return Json.parse(EntityDetails.serializer(), jsonBody)
    }

}

private fun String.asBearerToken() = "Bearer $this"