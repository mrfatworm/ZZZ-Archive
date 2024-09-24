/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    @SerialName("img_id")
    val id: Int,
    @SerialName("artwork_url")
    val artworkUrl: String,
    @SerialName("author_url")
    val authorUrl: String,
    @SerialName("artwork_name")
    val artworkName: String,
    @SerialName("author_name")
    val authorName: String,
) {
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Banner/$id.webp"
    }
}

val stubBannerResponse = BannerResponse(
    id = 1,
    artworkUrl = "https://zzz-archive.com/mrfatworm/1",
    authorUrl = "https://zzz-archive.com/mrfatworm",
    artworkName = "banner",
    authorName = "mrfatworm"
)

