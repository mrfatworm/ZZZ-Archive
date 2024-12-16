/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.NewsBannerResponse
import feature.news.data.OfficialNewsListItem
import feature.news.data.OfficialNewsRepository
import feature.news.model.OfficialNewsState
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class OfficialNewsUseCase(
    private val officialNewsRepository: OfficialNewsRepository,
    private val languageUseCase: LanguageUseCase
) {

    fun getNewsPeriodically(
        perMinutes: Int, amount: Int
    ): Flow<Result<List<OfficialNewsListItem>>> = flow {
        while (true) {
            val languageNewsCode = languageUseCase.getLanguage().first().officialCode
            emit(officialNewsRepository.getNews(amount, languageNewsCode))
            delay(perMinutes * 60 * 1000L)
        }
    }

    suspend fun convertToOfficialNewsState(newsList: List<OfficialNewsListItem>): List<OfficialNewsState> {
        val languageNewsCode = languageUseCase.getLanguage().first().officialCode
        return newsList.map {
            OfficialNewsState(
                title = it.sTitle,
                description = it.sIntro,
                imageUrl = getImageUrl(it.sExt),
                date = it.dtStartTime.split(" ")[0],
                newsUrl = "https://zenless.hoyoverse.com/$languageNewsCode/news/${it.iInfoId}"
            )
        }
    }

    private fun getImageUrl(toDecodeImageString: String): String {
        val json = Json { ignoreUnknownKeys = true }
        val newsBannerResponse = json.decodeFromString<NewsBannerResponse>(toDecodeImageString)
        return newsBannerResponse.newsBanner.firstOrNull()?.url ?: ""
    }
}