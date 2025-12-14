/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.FakeOfficialNewsRepository
import feature.news.data.mapper.toOfficialNewsList
import feature.news.model.stubOfficialNewsDataResponseResponse
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.Language

class OfficialNewsUseCaseTest {
    private val newsRepository = FakeOfficialNewsRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val officialNewsUseCase =
        OfficialNewsUseCase(officialNewsRepository = newsRepository, languageUseCase = languageUseCase)

    @Test
    fun `Get news success`() = runTest {
        val result = officialNewsUseCase.getNewsPeriodically(perMinutes = 10, amount = 0).first().getOrNull()
        assertEquals(stubOfficialNewsDataResponseResponse, result)
    }

    @Test
    fun `Get news fail`() = runTest {
        newsRepository.setError(true)
        val result = officialNewsUseCase.getNewsPeriodically(perMinutes = 10, amount = 0).first().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get new every 10 minutes`() = runTest {
        val result = officialNewsUseCase.getNews(10).getOrNull()
        assertEquals(stubOfficialNewsDataResponseResponse, result)
    }

    @Test
    fun `Get new every 10 minutes error`() = runTest {
        newsRepository.setError(true)
        val result = officialNewsUseCase.getNews(10).getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get news list success`() = runTest {
        val result = officialNewsUseCase.getNewsList(10).getOrNull()
        assertEquals(stubOfficialNewsDataResponseResponse.toOfficialNewsList(Language.English.officialCode), result)
    }

    @Test
    fun `Get news list periodically success`() = runTest {
        val result = officialNewsUseCase.getNewsListPeriodically(perMinutes = 10, amount = 0).first().getOrNull()
        assertEquals(stubOfficialNewsDataResponseResponse.toOfficialNewsList(Language.English.officialCode), result)
    }
}
