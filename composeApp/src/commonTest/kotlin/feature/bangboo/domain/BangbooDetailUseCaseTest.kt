/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.repository.FakeBangbooRepository
import feature.bangboo.model.stubBangbooDetail
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BangbooDetailUseCaseTest {

    private val bangbooRepository = FakeBangbooRepository()
    private val bangbooDetailUseCase = BangbooDetailUseCase(bangbooRepository)


    @Test
    fun `Get bangboo detail success`() = runTest {
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubBangbooDetail)
    }

    @Test
    fun `Get bangboo detail error`() = runTest {
        bangbooRepository.setError(true)
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}