package feature.forum.data

import feature.forum.model.AllForumResponse
import kotlinx.coroutines.withTimeout
import network.ForumHttp

class ForumRepositoryImpl(private val httpClient: ForumHttp) : ForumRepository {
    override suspend fun getAllForumList(): Result<AllForumResponse> {
        return try {
            val result = withTimeout(10000L) {
                httpClient.getAllForumList()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}