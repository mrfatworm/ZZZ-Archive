/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.wengine.model.WEngineDetail
import feature.wengine.model.WEngineDetailResponse
import utils.findAgentSpecialty
import utils.findRarity

fun WEngineDetailResponse.toWEngineDetail(path: String = ZzzConfig.ASSET_PATH): WEngineDetail {
    return WEngineDetail(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/$path/W-Engine/Image/$id.webp",
        isLeak = isLeak,
        rarity = findRarity(rarity),
        specialty = findAgentSpecialty(specialty),
        background = background,
        atk = 713,
        stat = stat,
        skill = skill,
        levelMaterials = levelMaterials
    )
}