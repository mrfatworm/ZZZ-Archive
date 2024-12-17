package feature.forum.data

import feature.forum.model.AllForumResponse
import feature.forum.model.stubAllForumResponse

class FakeForumRepository : ForumRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAllForumList(): Result<AllForumResponse> {
        return if (isError) {
            Result.failure(Exception("Fake error"))
        } else {
            Result.success(stubAllForumResponse)
        }
    }
}