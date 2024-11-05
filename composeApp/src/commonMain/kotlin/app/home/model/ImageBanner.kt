/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageBannerResponse(
    @SerialName("img_id")
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
    val authorName: String,
) {
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Banner/$id.webp"
    }
}

val stubImageBannerResponse = ImageBannerResponse(
    id = 1,
    artworkUrl = "https://zzz-archive.com/mrfatworm/1",
    artworkName = "banner",
    artworkDescription = "banner test",
    authorUrl = "https://zzz-archive.com/mrfatworm",
    authorName = "mrfatworm"
)

