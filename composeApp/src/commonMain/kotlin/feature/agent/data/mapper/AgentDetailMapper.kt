/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.agent.model.AgentDetail
import feature.agent.model.AgentDetailResponse
import feature.agent.model.Faction
import utils.findAgentAttackType
import utils.findAgentAttribute
import utils.findAgentSpecialty
import utils.findRarity

fun AgentDetailResponse.toAgentDetail(path: String = ZzzConfig.ASSET_PATH): AgentDetail {
    return AgentDetail(
        id = id,
        name = name,
        fullName = fullName,
        isLeak = isLeak,
        portraitUrl = "https://raw.githubusercontent.com/$path/Agent/Portrait/$id.webp",
        mindscapePartialUrl = "https://raw.githubusercontent.com/$path/Agent/Mindscape/Partial/$id.webp",
        mindscapeFullUrl = "https://raw.githubusercontent.com/$path/Agent/Mindscape/Full/$id.webp",
        rarity = findRarity(rarity),
        attribute = findAgentAttribute(attribute),
        specialty = findAgentSpecialty(specialty),
        attackType = findAgentAttackType(attackType),
        faction = Faction(factionId),
        agentBackground = agentBackground,
        basicData = basicData,
        skills = skills,
        mindscapeCinema = mindscapeCinema,
        levelMaterial = levelMaterial,
        suggestWEngines = suggestWEngines,
        suggestDrives = suggestDrives
    )
}