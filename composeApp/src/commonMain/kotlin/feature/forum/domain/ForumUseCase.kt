package feature.forum.domain

import feature.forum.data.ForumRepository
import feature.forum.model.AllForumResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForumUseCase(private val repository: ForumRepository) {
    suspend fun getAllForumList(): Result<AllForumResponse> {
        repository.getAllForumList().fold(
            onSuccess = {
                return Result.success(it)
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    fun getAllForumListPeriodically(intervalMinutes: Int): Flow<AllForumResponse> = flow {
        while (true) {
            repository.getAllForumList().fold(
                onSuccess = {
                    emit(it)
                },
                onFailure = {
                    println("get all forum list error: ${it.message}")
                }
            )
            delay(intervalMinutes * 60 * 1000L)
        }
    }
}