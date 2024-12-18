package feature.forum.domain

import feature.forum.data.ForumRepository
import feature.forum.data.mapper.toAllForumState
import feature.forum.model.AllForumState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForumUseCase(private val repository: ForumRepository) {
    suspend fun getAllForumList(): Result<AllForumState> {
        repository.getAllForumList().fold(
            onSuccess = {
                return Result.success(it.toAllForumState())
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    fun getAllForumListPeriodically(intervalMinutes: Int): Flow<AllForumState> = flow {
        while (true) {
            repository.getAllForumList().fold(
                onSuccess = {
                    emit(it.toAllForumState())
                },
                onFailure = {
                    println("get all forum list error: ${it.message}")
                }
            )
            delay(intervalMinutes * 60 * 1000L)
        }
    }
}