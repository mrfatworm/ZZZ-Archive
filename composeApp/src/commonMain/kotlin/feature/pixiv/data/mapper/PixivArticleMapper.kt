/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.data.mapper

import feature.pixiv.model.PixivArticleItem
import feature.pixiv.model.PixivTopicResponse

fun PixivTopicResponse.toPixivArticleList(): List<PixivArticleItem> =
    this.body?.illustManga?.data?.map { recentArticle ->
        PixivArticleItem(
            id = recentArticle.id.orEmpty(),
            title = recentArticle.title.orEmpty(),
            artworkUrl =
                if (recentArticle.id == null) {
                    "https://www.pixiv.net"
                } else {
                    "https://www.pixiv.net/artworks/${recentArticle.id}"
                },
            artworkImageUrl = recentArticle.url.orEmpty(),
            profileId = recentArticle.userId.orEmpty(),
            profileName = recentArticle.userName.orEmpty(),
            profileUrl =
                if (recentArticle.userId == null) {
                    "https://www.pixiv.net"
                } else {
                    "https://www.pixiv.net/users/${recentArticle.userId}"
                },
            profileImageUrl = recentArticle.profileImageUrl.orEmpty()
        )
    } ?: emptyList()
