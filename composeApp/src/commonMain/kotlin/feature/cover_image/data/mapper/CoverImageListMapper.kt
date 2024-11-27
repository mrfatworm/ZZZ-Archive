/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.mapper

import feature.cover_image.data.database.CoverImageListItemEntity
import feature.cover_image.model.CoverImageListItemResponse

fun CoverImageListItemResponse.toCoverImageListItemEntity(): CoverImageListItemEntity {
    return CoverImageListItemEntity(
        id = id,
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Banner/$id.webp",
        artworkUrl = artworkUrl,
        artworkName = artworkName,
        artworkDescription = artworkDescription,
        authorUrl = authorUrl,
        authorName = authorName
    )
}