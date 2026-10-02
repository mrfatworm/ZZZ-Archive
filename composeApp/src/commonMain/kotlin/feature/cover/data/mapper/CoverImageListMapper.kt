/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover.data.mapper

import feature.cover.data.database.CoverImageListItemEntity
import feature.cover.model.CoverImageListItemResponse
import network.assetUrl

fun CoverImageListItemResponse.toCoverImageListItemEntity(): CoverImageListItemEntity = CoverImageListItemEntity(
    id = id,
    imageUrl = assetUrl("Banner/$id.webp"),
    artworkUrl = artworkUrl,
    artworkName = artworkName,
    artworkDescription = artworkDescription,
    authorUrl = authorUrl,
    authorName = authorName
)
