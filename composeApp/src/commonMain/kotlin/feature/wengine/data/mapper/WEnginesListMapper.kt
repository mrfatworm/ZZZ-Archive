/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.wengine.data.database.WEnginesListItemEntity
import feature.wengine.model.WEnginesListItem
import feature.wengine.model.WEnginesListItemResponse
import utils.findAgentSpecialty
import utils.findRarity

fun WEnginesListItemResponse.toWEnginesListItemEntity(path: String = ZzzConfig.ASSET_PATH): WEnginesListItemEntity {
    return WEnginesListItemEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/$path/W-Engine/Image/$id.webp",
        isLeak = isLeak,
        rarity = rarity,
        specialty = specialty
    )
}

fun WEnginesListItemEntity.toWEnginesListItem(): WEnginesListItem {
    return WEnginesListItem(
        id = id,
        name = name,
        imageUrl = imageUrl,
        isLeak = isLeak,
        rarity = findRarity(rarity),
        specialty = findAgentSpecialty(specialty)
    )

}