package network

import feature.forum.model.AllForumResponse
import feature.forum.model.stubAllForumResponse

class FakeForumHttp : ForumHttp {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAllForumList(): AllForumResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubAllForumResponse
    }
}