package network

import feature.forum.model.AllForumResponse

interface ForumHttp {
    suspend fun getAllForumList(): AllForumResponse
}