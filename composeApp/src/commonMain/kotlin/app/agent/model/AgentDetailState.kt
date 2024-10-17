/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model


data class AgentDetailState(
    val agentDetail: AgentDetailResponse = emptyAgentDetailResponse,
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
        hp = 0, atk = 0, def = 0, coreSkillEnhancements = emptyList()
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