/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.FakeOfficialOfficialNewsRepository
import feature.news.data.stubOfficialNewsDataResponse
import feature.setting.domain.FakeLanguageUseCase
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class OfficialNewsUseCaseTest {

    private val newsRepository = FakeOfficialOfficialNewsRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val officialNewsUseCase = OfficialNewsUseCaseImpl(newsRepository, languageUseCase)


    @Test
    fun `Get New Every 10 Minutes`() = runTest {
        val result = officialNewsUseCase.getNewsPeriodically(10, 0).first() as ZzzResult.Success
        assertEquals(result.data, stubOfficialNewsDataResponse.data.list)
    }

    @Test
    fun `Get New Every 10 Minutes Fail`() = runTest {
        newsRepository.setError(true)
        val result = officialNewsUseCase.getNewsPeriodically(10, 0).first() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}