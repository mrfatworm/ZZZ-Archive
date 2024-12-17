/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.domain

import feature.cover_image.data.FakeCoverImageRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CoverImageUseCaseTest {
    private val coverImageRepository = FakeCoverImageRepository()
    private val coverImageUseCase = CoverImageUseCase(coverImageRepository)

    @Test
    fun `Get cover image list`() = runTest {
        val result = coverImageUseCase.invoke().first()
        assertEquals("mrfatworm", result.first().authorName)
    }

    @Test
    fun `Request cover image list success`() = runTest {
        val result = coverImageUseCase.updateCoverImagesList().getOrNull()
        assertEquals(Unit, result)
    }

    @Test
    fun `Request cover image list error`() = runTest {
        coverImageRepository.setError(true)
        val result = coverImageUseCase.updateCoverImagesList().getOrNull()
        assertNull(result)
    }
}