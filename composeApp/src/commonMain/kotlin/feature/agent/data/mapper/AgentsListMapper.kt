/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.agent.data.database.AgentsListItemEntity
import feature.agent.model.AgentListItem
import feature.agent.model.AgentListItemResponse
import utils.findAgentAttackType
import utils.findAgentAttribute
import utils.findAgentSpecialty
import utils.findRarity

fun AgentListItemResponse.toAgentsListItemEntity(path: String = ZzzConfig.ASSET_PATH): AgentsListItemEntity {
    return AgentsListItemEntity(
        id = id,
        name = name,
        fullName = fullName,
        imageUrl = "https://raw.githubusercontent.com/$path/Agent/Profile/$id.webp",
        isLeak = isLeak,
        rarity = rarity,
        specialty = specialty,
        attribute = attribute,
        attackType = attackType,
        factionId = factionId
    )
}

fun AgentsListItemEntity.toAgentListItem(): AgentListItem {
    return AgentListItem(
        id = id,
        name = name,
        fullName = fullName,
        imageUrl = imageUrl,
        isLeak = isLeak,
        rarity = findRarity(rarity),
        specialty = findAgentSpecialty(specialty),
        attribute = findAgentAttribute(attribute),
        attackType = findAgentAttackType(attackType),
        factionId = factionId
    )
}