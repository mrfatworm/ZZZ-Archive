/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import network.assetUrl

@Entity
data class CoverImageListItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val imageUrl: String,
    val artworkUrl: String,
    val artworkName: String,
    val artworkDescription: String,
    val authorUrl: String,
    val authorName: String
)

@Suppress("ktlint:standard:max-line-length")
val stubCoverImageListItemEntity =
    CoverImageListItemEntity(
        id = 1,
        imageUrl = assetUrl("Banner/1.webp"),
        artworkUrl = "https://www.pixiv.net/artworks/124677174",
        artworkName = "banner",
        artworkDescription = "banner test",
        authorUrl = "https://www.pixiv.net/users/9060892",
        authorName = "mrfatworm"
    )
