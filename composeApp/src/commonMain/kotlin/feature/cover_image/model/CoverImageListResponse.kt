/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoverImageListResponse(
    @SerialName("cover_images")
    val coverImages: List<CoverImageListItemResponse>
)

@Serializable
data class CoverImageListItemResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("artwork_url")
    val artworkUrl: String,
    @SerialName("artwork_name")
    val artworkName: String,
    @SerialName("artwork_description")
    val artworkDescription: String,
    @SerialName("author_url")
    val authorUrl: String,
    @SerialName("author_name")
    val authorName: String
)

val stubCoverImageResponse = CoverImageListResponse(
    listOf(
        CoverImageListItemResponse(
            id = 1,
            artworkUrl = "https://www.pixiv.net/artworks/124677174",
            artworkName = "Bangboo",
            artworkDescription = "ZZZ Archive Logo",
            authorUrl = "https://www.pixiv.net/users/9060892",
            authorName = "mrfatworm"
        ),
        CoverImageListItemResponse(
            id = 2,
            artworkUrl = "https://www.pixiv.net/artworks/124677174",
            artworkName = "banner2",
            artworkDescription = "banner test2",
            authorUrl = "https://www.pixiv.net/users/9060892",
            authorName = "mrfatworm"
        )
    )
)

