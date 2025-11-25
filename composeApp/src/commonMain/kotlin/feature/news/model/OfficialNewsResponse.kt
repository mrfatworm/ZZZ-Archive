/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficialNewsResponse(
    @SerialName("retcode") val retCode: Int? = null,
    val message: String? = null,
    val data: OfficialNewsDataResponse? = null
)

@Serializable
data class OfficialNewsDataResponse(val iTotal: Int? = null, val list: List<OfficialNewsListItemResponse>? = null)

@Serializable
data class OfficialNewsListItemResponse(
    val sChanId: List<String>? = null,
    val sTitle: String? = null,
    val sIntro: String? = null,
    val sUrl: String? = null,
    val sAuthor: String? = null,
    val sContent: String? = null,
    val sExt: String? = null,
    val dtStartTime: String? = null,
    val dtEndTime: String? = null,
    val dtCreateTime: String? = null,
    val iInfoId: Int? = null,
    val sTagName: List<String>? = null,
    val sCategoryName: String? = null,
    val sSign: String? = null
)

@Serializable
data class NewsBannerResponse(
    @SerialName("news-banner")
    val newsBanner: List<NewsBannerItemResponse>? = null,
    @SerialName("news-self-path")
    val newsSelfPath: String? = null
)

@Serializable
data class NewsBannerItemResponse(val name: String? = null, val url: String? = null)

@Suppress("ktlint:standard:max-line-length")
val stubNewsListItem =
    OfficialNewsListItemResponse(
        sChanId = listOf("296"),
        sTitle = "《絕區零》凱撒角色展示｜卡呂冬的騎行",
        sIntro = "對了，這些人為什麼來招惹我們？」",
        sUrl = "",
        sAuthor = "",
        sContent = "",
        sExt = "{\"news-banner\":[{\"name\":\"CHT-1920x1080.jpg\",\"url\":\"https://fastcdn.hoyoverse.com/content-v2/nap/126022/93934296a401f3337f65e4fd938ea7e4_7828096096202056509.jpg\"}],\"news-self-path\":\"-public\"}",
        dtStartTime = "2024-09-21 12:00:00",
        dtEndTime = "2035-01-01 00:00:00",
        dtCreateTime = "2024-09-21 12:00:00",
        iInfoId = 126022,
        sTagName = emptyList(),
        sCategoryName = "",
        sSign = ""
    )

val stubOfficialNewsDataResponseResponse =
    OfficialNewsResponse(
        retCode = 0,
        message = "OK",
        data =
            OfficialNewsDataResponse(
                iTotal = 396,
                list =
                    listOf(
                        stubNewsListItem
                    )
            )
    )
