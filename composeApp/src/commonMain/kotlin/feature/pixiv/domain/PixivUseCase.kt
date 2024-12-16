/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.domain

import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivTopicResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PixivUseCase(private val repository: PixivRepository) {
    fun invoke(zzzTag: String): Flow<Result<PixivTopicResponse>> = flow {
        emit(repository.getZzzTopic(zzzTag))
    }
}