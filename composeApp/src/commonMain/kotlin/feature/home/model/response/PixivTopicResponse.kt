/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixivTopicResponse(val body: Body) {
    fun getPopularArticles(): List<RecentArticle> {
        return body.illustManga.data
    }
}

@Serializable
@SerialName("illustManga")
data class Body(val illustManga: IllustManga)

@Serializable
data class IllustManga(
    @SerialName("data")
    val data: List<RecentArticle>
)

@Serializable
data class RecentArticle(
    val id: String,
    val title: String?,
    val url: String,
    val userId: String,
    val userName: String,
    val profileImageUrl: String
)

val stubPixivTopicResponse = PixivTopicResponse(
    Body(
        IllustManga(
            listOf(
                RecentArticle(
                    id = "123456789",
                    title = "Ellen",
                    url = "https://i.pximg.net",
                    userId = "87654321",
                    userName = "mrfatworm",
                    profileImageUrl = "https://i.pximg.net"
                )
            )
        )
    )
)