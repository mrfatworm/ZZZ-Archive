/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.agent.data.database.AgentsListItemEntity
import feature.agent.model.AgentListItem
import feature.agent.model.AgentListItemResponse
import feature.agent.model.Faction
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.findAgentAttribute
import utils.findAgentSpecialty
import utils.findRarity

fun AgentListItemResponse.toAgentsListItemEntity(path: String = ZzzConfig.ASSET_PATH): AgentsListItemEntity =
    AgentsListItemEntity(
        id = id ?: 0,
        name = name.orEmpty(),
        imageUrl = "https://raw.githubusercontent.com/$path/Agent/Profile/$id.webp",
        isHighlight = isHighlight ?: false,
        rarity = rarity ?: 0,
        specialty = specialty.orEmpty(),
        attribute = attribute.orEmpty(),
        factionId = factionId ?: 0,
        materialId = material ?: 0,
        weeklyMaterialId = weeklyMaterial ?: 0,
        portraitCount = portraitCount ?: 1
    )

fun AgentsListItemEntity.toAgentListItem(): AgentListItem = AgentListItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isHighlight = isHighlight,
    rarity = findRarity(rarity),
    specialty = findAgentSpecialty(specialty),
    attribute = findAgentAttribute(attribute),
    faction = Faction(factionId),
    materialUrl = getMaterialUrl(id = materialId),
    weeklyMaterialUrl = getMaterialUrl(id = weeklyMaterialId),
    levelMaterialUrls = when (findAgentSpecialty(specialty)) {
        AgentSpecialty.Attack -> listOf(17, 18, 19)
        AgentSpecialty.Stun -> listOf(20, 21, 22)
        AgentSpecialty.Anomaly -> listOf(23, 24, 25)
        AgentSpecialty.Support -> listOf(26, 27, 28)
        AgentSpecialty.Defense -> listOf(29, 30, 31)
        AgentSpecialty.Rupture -> listOf(69, 70, 71)
        AgentSpecialty.None -> emptyList()
    }.map { getMaterialUrl(id = it) },
    skillMaterialUrls = when (findAgentAttribute(attribute)) {
        AgentAttribute.Physical -> listOf(53, 54, 55)
        AgentAttribute.Fire -> listOf(56, 57, 58)
        AgentAttribute.Ice -> listOf(59, 60, 61)
        AgentAttribute.Electric -> listOf(62, 63, 64)
        AgentAttribute.Ether -> listOf(65, 66, 67)
        AgentAttribute.None -> emptyList()
    }.map { getMaterialUrl(id = it) },
    wEngineMaterialUrls = when (findAgentSpecialty(specialty)) {
        AgentSpecialty.Attack -> listOf(32, 33, 34)
        AgentSpecialty.Stun -> listOf(35, 36, 37)
        AgentSpecialty.Anomaly -> listOf(38, 39, 40)
        AgentSpecialty.Support -> listOf(41, 42, 43)
        AgentSpecialty.Defense -> listOf(44, 45, 46)
        AgentSpecialty.Rupture -> listOf(72, 73, 74)
        AgentSpecialty.None -> emptyList()
    }.map { getMaterialUrl(id = it) },
    portraitCount = portraitCount
)

private fun getMaterialUrl(
    path: String = ZzzConfig.ASSET_PATH,
    id: Int
): String = "https://raw.githubusercontent.com/$path/Material/$id.webp"
