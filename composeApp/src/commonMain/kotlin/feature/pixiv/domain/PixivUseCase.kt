/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.domain

import feature.pixiv.data.PixivRepository

class PixivUseCase(private val repository: PixivRepository) {
    suspend fun invoke(zzzTag: String) =
        repository.getZzzTopic(zzzTag).map { it.body.illustManga.data }
}