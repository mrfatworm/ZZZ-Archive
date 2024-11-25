package network

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.banner.data.BannerResponse
import feature.cover_image.data.CoverImageResponse
import feature.drive.model.DrivesListResponse
import feature.setting.domain.LanguageUseCase
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class ZzzHttpImpl(engine: HttpClientEngine, languageUseCase: LanguageUseCase) : ZzzHttp {
    override val defaultTimeout = 5000L
    override val languagePath = languageUseCase.getLanguage().officialNewsCode

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

    override suspend fun requestImageBanner(): CoverImageResponse {
        return requestData("ImageBanner.json")
    }

    override suspend fun requestAgentsList(): AgentsListResponse {
        return requestData("Agent/$languagePath/List.json")
    }

    override suspend fun requestAgentDetail(id: Int): AgentDetailResponse {
        return requestData("Agent/$languagePath/Detail/$id.json")
    }

    override suspend fun requestWEnginesList(): WEnginesListResponse {
        return requestData("W-Engine/$languagePath/List.json")
    }

    override suspend fun requestWEngineDetail(id: Int): WEngineDetailResponse {
        return requestData("W-Engine/$languagePath/Detail/$id.json")
    }

    override suspend fun requestBangbooList(): BangbooListResponse {
        return requestData("Bangboo/$languagePath/List.json")
    }

    override suspend fun requestBangbooDetail(id: Int): BangbooDetailResponse {
        return requestData("Bangboo/$languagePath/Detail/$id.json")
    }

    override suspend fun requestDrivesList(): DrivesListResponse {
        return requestData("Drive/$languagePath/List.json")
    }
}


