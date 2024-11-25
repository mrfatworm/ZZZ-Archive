/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficialNewsResponse(
    @SerialName("retcode") val retCode: Int, val message: String, val data: OfficialNewsData
)

@Serializable
data class OfficialNewsData(
    val iTotal: Int, val list: List<OfficialNewsListItem>
)

@Serializable
data class OfficialNewsListItem(
    val sTitle: String,
    val sIntro: String,
    val sExt: String,
    val dtStartTime: String,
    val iInfoId: Int
)

@Serializable
data class NewsBannerItem(val name: String, val url: String)

@Serializable
data class NewsBannerResponse(
    @SerialName("news-banner")
    val newsBanner: List<NewsBannerItem>
)

val stubNewsListItem = OfficialNewsListItem(
    sTitle = "《絕區零》凱撒角色展示｜卡呂冬的騎行",
    sIntro = "對了，這些人為什麼來招惹我們？」",
    sExt = "{\\\"news-banner\\\":[{\\\"name\\\":\\\"CHT-1920x1080.jpg\\\",\\\"url\\\":\\\"https://fastcdn.hoyoverse.com/content-v2/nap/126022/93934296a401f3337f65e4fd938ea7e4_7828096096202056509.jpg\\\"}]}",
    dtStartTime = "2024-09-21 12:00:00",
    iInfoId = 126022
)

val stubOfficialNewsDataResponse = OfficialNewsResponse(
    retCode = 0, message = "OK", data = OfficialNewsData(
        iTotal = 396, list = listOf(
            stubNewsListItem
        )
    )
)
