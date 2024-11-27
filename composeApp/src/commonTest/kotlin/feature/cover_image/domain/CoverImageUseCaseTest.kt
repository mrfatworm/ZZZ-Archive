/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.domain

import feature.cover_image.data.FakeCoverImageRepository
import feature.cover_image.model.stubCoverImageResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CoverImageUseCaseTest {
    private val coverImageRepository = FakeCoverImageRepository()
    private val coverImageUseCase = CoverImageUseCase(coverImageRepository)

    @Test
    fun `Get Cover Image Success`() = runTest {
        val result = coverImageUseCase.invoke().getOrNull()
        assertEquals(result, stubCoverImageResponse)
    }

    @Test
    fun `Get Cover Image Error`() = runTest {
        coverImageRepository.setError(true)
        val result = coverImageUseCase.invoke().getOrNull()
        assertNull(result)
    }
}