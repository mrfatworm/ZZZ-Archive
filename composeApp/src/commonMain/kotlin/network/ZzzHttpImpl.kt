package network

import feature.agent.model.AgentsListResponse
import feature.banner.data.BannerResponse
import feature.cover.model.CoverImageListResponse
import feature.home.model.AssetVersionResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json

class ZzzHttpImpl(engine: HttpClientEngine) : ZzzHttp {
    private val client = createZzzHttpClient(engine)

    private suspend inline fun <reified T> requestData(path: String): T {
        val result =
            client
                .get {
                    url.appendPathSegments(path)
                }.bodyAsText()
        return Json.decodeFromString(result)
    }

    override suspend fun requestAssetVersion(): AssetVersionResponse = requestData("Version.json")

    override suspend fun requestBanner(languagePath: String): BannerResponse =
        requestData("Banner/$languagePath/Banner.json")

    override suspend fun requestCoverImage(): CoverImageListResponse = requestData("CoverImagesList.json")

    override suspend fun requestAgentsList(): AgentsListResponse = requestData("Agent/List.json")
}
