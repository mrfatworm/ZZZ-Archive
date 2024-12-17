package network

import feature.forum.model.AllForumResponse
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ForumHttpImpl(engine: HttpClientEngine) : ForumHttp {
    private val client = createForumHttpClient(engine)

    override suspend fun getAllForumList(): AllForumResponse = client.get {
        contentType(ContentType.Application.Json)
    }.body()
}