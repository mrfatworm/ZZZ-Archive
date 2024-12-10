/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.model


data class OfficialNewsState(
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: String,
    val newsUrl: String
)

val stubOfficialNewsState = OfficialNewsState(
    title = "《絕區零》凱撒角色展示｜卡呂冬的騎行",
    description = "對了，這些人為什麼來招惹我們？」",
    imageUrl = "https://fastcdn.hoyoverse.com/content-v2/nap/126022/93934296a401f3337f65e4fd938ea7",
    date = "2024-09-21",
    newsUrl = "https://zenless.hoyoverse.com/en-us/news/126022"
)