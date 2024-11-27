/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.model

import feature.drive.data.database.DrivesListItemEntity
import feature.drive.model.DriveListItemResponse
import utils.AgentAttackType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


data class AgentDetailState(
    val agentDetail: AgentDetail = emptyAgentDetail,
    val drivesList: List<DrivesListItemEntity> = emptyList(),
    val selectedDrive: DriveListItemResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

val emptyAgentDetail = AgentDetail(
    id = 0,
    name = "",
    fullName = "",
    isLeak = false,
    portraitUrl = "",
    mindscapePartialUrl = "",
    mindscapeFullUrl = "",
    rarity = ZzzRarity.RANK_D,
    specialty = AgentSpecialty.None,
    attribute = AgentAttribute.None,
    attackType = AgentAttackType.None,
    faction = Faction(0),
    agentBackground = "---",
    basicData = AgentBasicData(
        hp = 0, atk = 0, def = 0, nameAndValues = emptyList()
    ),
    skills = AgentSkill(
        basicAttack = emptyList(),
        dodge = emptyList(),
        dashAttack = emptyList(),
        dodgeCounter = emptyList(),
        quickAssist = emptyList(),
        defensiveAssist = emptyList(),
        assistFollowUp = emptyList(),
        specialAttack = emptyList(),
        exSpecialAttack = emptyList(),
        chainAttack = emptyList(),
        ultimate = emptyList(),
        corePassive = emptyList(),
        additionalAbility = emptyList(),
    ),
    mindscapeCinema = emptyList(),
    levelMaterial = AgentLevelMaterial(
        skillTen = emptyList(), skillMax = emptyList()
    ),
    suggestWEngines = emptyList(),
    suggestDrives = emptyList()
)