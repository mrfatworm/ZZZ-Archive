/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.agent.model.AgentListItem
import feature.agent.model.AgentListItemResponse
import utils.AgentAttackType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

fun AgentListItemResponse.toAgentListItem(path: String = ZzzConfig.ASSET_PATH): AgentListItem {
    return AgentListItem(
        id = id,
        name = name,
        fullName = fullName,
        imageUrl = "https://raw.githubusercontent.com/$path/Agent/Profile/$id.webp",
        isLeak = isLeak,
        rarity = ZzzRarity.entries.find { it.level == rarity } ?: ZzzRarity.RANK_D,
        specialty = AgentSpecialty.entries.find { it.name.lowercase() == specialty.lowercase() }
            ?: AgentSpecialty.None,
        attribute = AgentAttribute.entries.find { it.name.lowercase() == attribute.lowercase() }
            ?: AgentAttribute.None,
        attackType = AgentAttackType.entries.find { it.name.lowercase() == attackType.lowercase() }
            ?: AgentAttackType.None,
        factionId = factionId
    )
}