package network

import app.agent.model.AgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.drive.model.DriveListResponse
import app.home.model.BannerResponse
import app.wengine.model.WEnginesListResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class ZzzHttpImpl(engine: HttpClientEngine) : ZzzHttp {
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
        return requestData("Banner.json")
    }

    override suspend fun requestAgentList(): AgentsListResponse {
        return requestData("Agent/zh-tw/List.json")
    }

    override suspend fun requestWEngineList(): WEnginesListResponse {
        return requestData("W-Engine/zh-tw/List.json")
    }

    override suspend fun requestBangbooList(): BangbooListResponse {
        return requestData("Bangboo/zh-tw/List.json")
    }

    override suspend fun requestDriveList(): DriveListResponse {
        return requestData("Drive/zh-tw/List.json")
    }
}


