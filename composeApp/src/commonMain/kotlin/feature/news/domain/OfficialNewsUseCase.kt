/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.NewsBannerResponse
import feature.news.data.OfficialNewsListItem
import feature.news.data.OfficialNewsRepository
import feature.news.presentation.OfficialNewsState
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import utils.ZzzResult

interface OfficialNewsUseCase {
    fun getNewsPeriodically(
        perMinutes: Int, amount: Int
    ): Flow<ZzzResult<List<OfficialNewsListItem>>>

    fun convertToOfficialNewsState(newsList: List<OfficialNewsListItem>): List<OfficialNewsState>
}

class OfficialNewsUseCaseImpl(
    private val officialNewsRepository: OfficialNewsRepository,
    private val languageUseCase: LanguageUseCase
) : OfficialNewsUseCase {

    override fun getNewsPeriodically(
        perMinutes: Int, amount: Int
    ): Flow<ZzzResult<List<OfficialNewsListItem>>> = flow {
        while (true) {
            emit(officialNewsRepository.getNews(amount))
            delay(perMinutes * 60 * 1000L)
        }
    }.flowOn(Dispatchers.IO)

    override fun convertToOfficialNewsState(newsList: List<OfficialNewsListItem>): List<OfficialNewsState> {
        val languageNewsCode = languageUseCase.getLanguage().officialNewsCode
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