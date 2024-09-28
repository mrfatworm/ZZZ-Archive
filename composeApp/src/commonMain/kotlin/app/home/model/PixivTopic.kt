package app.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixivZzzTopic(val body: Body) {
    fun getPopularArticles(): List<RecentArticle> {
        return body.popular.recentArticlesList
    }
}

@Serializable
data class Body(val popular: Popular)

@Serializable
data class Popular(
    @SerialName("recent") val recentArticlesList: List<RecentArticle>
)

@Serializable
data class RecentArticle(
    val id: String,
    val title: String,
    val url: String,
    val userId: String,
    val userName: String,
    val createDate: String,
    val aiType: Int,
    val profileImageUrl: String
)

val stubPixivZzzTopic = PixivZzzTopic(
    Body(
        Popular(
            listOf(
                RecentArticle(
                    id = "123456789",
                    title = "Ellen",
                    url = "https://i.pximg.net",
                    userId = "87654321",
                    userName = "mrfatworm",
                    createDate = "2024-09-27T14:29:23+09:00",
                    aiType = 1,
                    profileImageUrl = "https://i.pximg.net"
                )
            )
        )
    )

)