package network

import app.home.model.OfficialActivities
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ZzzHttpClientImpl(engine: HttpClientEngine) : ZzzHttpClient {

    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    override suspend fun requestActivities(): OfficialActivities = client.get {
        url {
            takeFrom("https://sg-public-api-static.hoyoverse.com")
            path("/content_v2_user/app/3e9196a4b9274bd7/getContentList")
        }
        contentType(ContentType.Application.Json)
        parameter("iPageSize", 7)
        parameter("iPage", 1)
        parameter("iChanId", 288)
        parameter("sLangKey", "zh-tw")
    }.body()
}


