package network

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.banner.data.BannerResponse
import feature.cover_image.model.CoverImageListResponse
import feature.drive.model.DrivesListResponse
import feature.home.model.AssetVersionResponse
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class ZzzHttpImpl(engine: HttpClientEngine) : ZzzHttp {

    private val client = createZzzHttpClient(engine)

    private suspend inline fun <reified T> requestData(path: String): T {
        val result = client.get {
            url.appendPathSegments(path)
        }.bodyAsText()
        return Json.decodeFromString(result)
    }

    override suspend fun requestAssetVersion(): AssetVersionResponse {
        return requestData("Version.json")
    }

    override suspend fun requestBanner(languagePath: String): BannerResponse {
        return requestData("Banner/$languagePath/Banner.json")
    }

    override suspend fun requestCoverImage(): CoverImageListResponse {
        return requestData("CoverImagesList.json")
    }

    override suspend fun requestAgentsList(languagePath: String): AgentsListResponse {
        return requestData("Agent/$languagePath/List.json")
    }

    override suspend fun requestAgentDetail(id: Int, languagePath: String): AgentDetailResponse {
        return requestData("Agent/$languagePath/Detail/$id.json")
    }

    override suspend fun requestWEnginesList(languagePath: String): WEnginesListResponse {
        return requestData("W-Engine/$languagePath/List.json")
    }

    override suspend fun requestWEngineDetail(
        id: Int,
        languagePath: String
    ): WEngineDetailResponse {
        return requestData("W-Engine/$languagePath/Detail/$id.json")
    }

    override suspend fun requestBangbooList(languagePath: String): BangbooListResponse {
        return requestData("Bangboo/$languagePath/List.json")
    }

    override suspend fun requestBangbooDetail(
        id: Int,
        languagePath: String
    ): BangbooDetailResponse {
        return requestData("Bangboo/$languagePath/Detail/$id.json")
    }

    override suspend fun requestDrivesList(languagePath: String): DrivesListResponse {
        return requestData("Drive/$languagePath/List.json")
    }
}


