/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data.mapper

import feature.news.model.NewsBannerResponse
import feature.news.model.OfficialNewsListItem
import feature.news.model.OfficialNewsResponse
import kotlinx.serialization.json.Json

fun OfficialNewsResponse.toOfficialNewsList(officialCode: String): List<OfficialNewsListItem> =
    this.data?.list?.map { newsItem ->

        val imageUrl =
            try {
                val json =
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                val newsBannerResponse =
                    json.decodeFromString<NewsBannerResponse>(newsItem.sExt.orEmpty())
                newsBannerResponse.newsBanner?.firstOrNull()?.url ?: ""
            } catch (e: Exception) {
                ""
            }

        val formattedDate =
            try {
                newsItem.dtStartTime?.split(" ")?.firstOrNull() ?: newsItem.dtStartTime
            } catch (e: Exception) {
                newsItem.dtStartTime
            }

        OfficialNewsListItem(
            title = newsItem.sTitle.orEmpty(),
            description = newsItem.sIntro.orEmpty(),
            imageUrl = imageUrl,
            date = formattedDate.orEmpty(),
            newsUrl = "https://zenless.hoyoverse.com/$officialCode/news/${newsItem.iInfoId}"
        )
    } ?: emptyList()
