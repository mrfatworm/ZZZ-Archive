/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.bangboo.data.database.BangbooListItemEntity
import feature.bangboo.model.BangbooListItem
import feature.bangboo.model.BangbooListItemResponse
import utils.findAgentAttribute
import utils.findRarity

fun BangbooListItemResponse.toBangbooListItemEntity(path: String = ZzzConfig.ASSET_PATH): BangbooListItemEntity {
    return BangbooListItemEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/Bangboo/Profile/$id.webp",
        isLeak = isLeak,
        rarity = rarity,
        attribute = attribute
    )
}

fun BangbooListItemEntity.toBangbooListItem(): BangbooListItem {
    return BangbooListItem(
        id = id,
        name = name,
        imageUrl = imageUrl,
        isLeak = isLeak,
        rarity = findRarity(rarity),
        attribute = findAgentAttribute(attribute)
    )
}