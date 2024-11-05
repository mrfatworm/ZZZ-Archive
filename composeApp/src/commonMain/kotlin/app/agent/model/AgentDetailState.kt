/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.model

import app.drive.model.DriveListItem


data class AgentDetailState(
    val agentDetail: AgentDetailResponse = emptyAgentDetailResponse,
    val drivesList: List<DriveListItem> = emptyList(),
    val selectedDrive: DriveListItem? = null
)

val emptyAgentDetailResponse = AgentDetailResponse(
    id = 0,
    name = "",
    fullName = "",
    isLeak = false,
    rarity = 5,
    specialty = "",
    attribute = "",
    attackType = "",
    factionId = 0,
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