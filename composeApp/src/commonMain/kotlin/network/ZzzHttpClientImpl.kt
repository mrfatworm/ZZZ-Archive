package network

import app.agent.model.AgentsListResponse
import app.home.model.BannerResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class ZzzHttpClientImpl(engine: HttpClientEngine) : ZzzHttpClient {
    override val defaultTimeout = 5000L
    override val longTimeout = 10000L
    private val client = createZzzHttpClient(engine)

    private suspend inline fun <reified T> requestData(path: String): T {
        val result = client.get {
            url.appendPathSegments(path)
        }.bodyAsText()
        return Json.decodeFromString(result)
    }

    override suspend fun requestBanner(): BannerResponse {
        return requestData("banner.json")
    }

    override suspend fun requestAgentList(): AgentsListResponse {
        return requestData("zh-tw/agent/list.json")
    }
}


