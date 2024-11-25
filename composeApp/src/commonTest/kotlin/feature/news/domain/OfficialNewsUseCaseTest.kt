/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.FakeOfficialNewsRepository
import feature.news.data.stubOfficialNewsDataResponse
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class OfficialNewsUseCaseTest {

    private val newsRepository = FakeOfficialNewsRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val officialNewsUseCase = OfficialNewsUseCase(newsRepository, languageUseCase)


    @Test
    fun `Get New Every 10 Minutes`() = runTest {
        val result = officialNewsUseCase.getNewsPeriodically(10, 0).first().getOrNull()
        assertEquals(result, stubOfficialNewsDataResponse.data.list)
    }

    @Test
    fun `Get New Every 10 Minutes Failure`() = runTest {
        newsRepository.setError(true)
        val result = officialNewsUseCase.getNewsPeriodically(10, 0).first().getOrNull()
        assertNull(result)
    }
}