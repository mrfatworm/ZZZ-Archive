/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.bangboo.model.BangbooDetail
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.bangbooLevelMaterials
import utils.findAgentAttribute
import utils.findRarity

fun BangbooDetailResponse.toBangbooDetail(path: String = ZzzConfig.ASSET_PATH): BangbooDetail {
    return BangbooDetail(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/$path/Bangboo/Portrait/$id.webp",
        isLeak = isLeak,
        rarity = findRarity(rarity),
        attribute = findAgentAttribute(attribute),
        basicData = basicData,
        activeSkill = activeSkill,
        additionalAbility = additionalAbility,
        chainAttack = chainAttack,
        levelMaterials = bangbooLevelMaterials
    )
}