package feature.forum.data

import feature.forum.model.AllForumResponse
import network.ForumHttp

class ForumRepositoryImpl(private val httpClient: ForumHttp) : ForumRepository {
    override suspend fun getAllForumList(): Result<AllForumResponse> {
        return try {
            val result = httpClient.getAllForumList()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}