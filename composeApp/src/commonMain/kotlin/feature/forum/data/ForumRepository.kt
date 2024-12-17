package feature.forum.data

import feature.forum.model.AllForumResponse

interface ForumRepository {
    suspend fun getAllForumList(): Result<AllForumResponse>
}