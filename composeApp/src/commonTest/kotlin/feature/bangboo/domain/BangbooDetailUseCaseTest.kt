/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.repository.FakeBangbooRepository
import feature.bangboo.model.stubBangbooDetail
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BangbooDetailUseCaseTest {

    private val bangbooRepository = FakeBangbooRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val bangbooDetailUseCase = BangbooDetailUseCase(bangbooRepository, languageUseCase)


    @Test
    fun `Get bangboo detail success`() = runTest {
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertEquals(stubBangbooDetail, result)
    }

    @Test
    fun `Get bangboo detail error`() = runTest {
        bangbooRepository.setError(true)
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}