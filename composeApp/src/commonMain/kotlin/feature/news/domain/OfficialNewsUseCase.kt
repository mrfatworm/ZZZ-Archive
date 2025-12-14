/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.OfficialNewsRepository
import feature.news.data.mapper.toOfficialNewsList
import feature.news.model.OfficialNewsListItem
import feature.news.model.OfficialNewsResponse
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class OfficialNewsUseCase(
    private val officialNewsRepository: OfficialNewsRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun getNews(amount: Int): Result<OfficialNewsResponse> {
        val languageNewsCode = languageUseCase.getLanguage().first().officialCode
        return officialNewsRepository.getNews(amount = amount, languagePath = languageNewsCode)
    }

    fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<Result<OfficialNewsResponse>> = flow {
        while (true) {
            emit(getNews(amount))
            delay(perMinutes * 60 * 1000L)
        }
    }

    suspend fun getNewsList(amount: Int): Result<List<OfficialNewsListItem>> {
        val officialCode = languageUseCase.getLanguage().first().officialCode
        return getNews(amount).map { response ->
            response.toOfficialNewsList(officialCode)
        }
    }

    fun getNewsListPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<Result<List<OfficialNewsListItem>>> = flow {
        while (true) {
            emit(getNewsList(amount))
            delay(perMinutes * 60 * 1000L)
        }
    }
}
