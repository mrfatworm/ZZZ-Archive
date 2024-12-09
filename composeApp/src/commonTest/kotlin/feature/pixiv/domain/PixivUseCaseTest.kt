/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.domain

import feature.pixiv.data.FakePixivRepository
import feature.pixiv.data.stubPixivTopicResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PixivUseCaseTest {
    private val pixivRepository = FakePixivRepository()
    private val pixivUseCase = PixivUseCase(pixivRepository)

    @Test
    fun `Get Pixiv topics success`() = runTest {
        val result = pixivUseCase.invoke("").getOrNull()
        assertEquals(result, stubPixivTopicResponse.getPopularArticles())
    }

    @Test
    fun `Get Pixiv topics error`() = runTest {
        pixivRepository.setError(true)
        val result = pixivUseCase.invoke("").getOrNull()
        assertNull(result)
    }
}