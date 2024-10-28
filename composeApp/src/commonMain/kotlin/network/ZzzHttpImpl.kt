package network

import app.agent.model.AgentDetailResponse
import app.agent.model.AgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.drive.model.DriveListResponse
import app.home.model.ImageBannerResponse
import app.wengine.model.WEngineDetailResponse
import app.wengine.model.WEnginesListResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json
import mainfunc.model.BannerResponse
import utils.LanguageHandler

class ZzzHttpImpl(engine: HttpClientEngine, languageHandler: LanguageHandler) : ZzzHttp {
    override val defaultTimeout = 5000L
    override val longTimeout = 10000L
    override val languagePath = languageHandler.getLanguage().assetLang

    private val client = createZzzHttpClient(engine)

    private suspend inline fun <reified T> requestData(path: String): T {
        val result = client.get {
            url.appendPathSegments(path)
        }.bodyAsText()
        return Json.decodeFromString(result)
    }

    override suspend fun requestBanner(): BannerResponse {
        return requestData("Banner/$languagePath/Banner.json")
    }

    override suspend fun requestImageBanner(): ImageBannerResponse {
        return requestData("ImageBanner.json")
    }

    override suspend fun requestAgentList(): AgentsListResponse {
        return requestData("Agent/$languagePath/List.json")
    }

    override suspend fun requestAgentDetail(id: Int): AgentDetailResponse {
        return requestData("Agent/$languagePath/Detail/$id.json")
    }

    override suspend fun requestWEngineList(): WEnginesListResponse {
        return requestData("W-Engine/$languagePath/List.json")
    }

    override suspend fun requestWEngineDetail(id: Int): WEngineDetailResponse {
        return requestData("W-Engine/$languagePath/Detail/$id.json")
    }

    override suspend fun requestBangbooList(): BangbooListResponse {
        return requestData("Bangboo/$languagePath/List.json")
    }

    override suspend fun requestDriveList(): DriveListResponse {
        return requestData("Drive/$languagePath/List.json")
    }
}


