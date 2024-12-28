/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.model

import utils.AgentAttackType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


data class AgentDetail(
    val id: Int,
    val name: String,
    val fullName: String,
    val isLeak: Boolean,
    val portraitUrl: String,
    val mindscapePartialUrl: String,
    val mindscapeFullUrl: String,
    val rarity: ZzzRarity,
    val attribute: AgentAttribute,
    val specialty: AgentSpecialty,
    val attackType: AgentAttackType,
    val faction: Faction,
    val agentBackground: String,
    val basicData: AgentBasicData,
    val skills: AgentSkill,
    val mindscapeCinema: List<NameAndDesc>,
    val levelMaterial: AgentLevelMaterial,
    val suggestWEngines: List<RarityItem>,
    val suggestDrives: List<AgentDriveItem>
) {
    fun getHpAtkDef(): String {
        return "${basicData.hp} / ${basicData.atk} / ${basicData.def}"
    }
}

val stubAgentDetail = AgentDetail(
    id = 20,
    name = "青衣",
    fullName = "青衣",
    isLeak = false,
    portraitUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Portrait/20.webp",
    mindscapePartialUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Mindscape/Partial/20.webp",
    mindscapeFullUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Mindscape/Full/20.webp",
    rarity = ZzzRarity.RARITY_S,
    attribute = AgentAttribute.Electric,
    specialty = AgentSpecialty.Stun,
    attackType = AgentAttackType.Strike,
    faction = Faction(6),
    agentBackground = "青衣背景",
    basicData = AgentBasicData(
        hp = 8250, atk = 683, def = 612, nameAndValues = listOf(
            NameAndValue(
                name = "攻擊力", value = "+75"
            ), NameAndValue(
                name = "衝擊力", value = "+18"
            )
        )
    ),
    skills = AgentSkill(
        basicAttack = listOf(
            NameAndDesc(
                name = "一煞", description = "一煞描述"
            ), NameAndDesc(
                name = "醉花雲", description = "醉花雲描述"
            ), NameAndDesc(
                name = "醉花月雲轉", description = "醉花月雲轉描述"
            ), NameAndDesc(
                name = "閃絡", description = "閃絡描述"
            )
        ),
        dodge = listOf(
            NameAndDesc(
                name = "雁過聲", description = "雁過聲描述"
            )
        ),
        dashAttack = listOf(
            NameAndDesc(
                name = "入破", description = "入破描述"
            )
        ),
        dodgeCounter = listOf(
            NameAndDesc(
                name = "意不盡", description = "意不盡描述"
            )
        ),
        quickAssist = listOf(
            NameAndDesc(
                name = "風入松", description = "風入松描述"
            )
        ),
        defensiveAssist = listOf(
            NameAndDesc(
                name = "錦上花", description = "錦上花描述"
            )
        ),
        assistFollowUp = listOf(
            NameAndDesc(
                name = "清江引", description = "清江引描述"
            )
        ),
        specialAttack = listOf(
            NameAndDesc(
                name = "constituent", description = "constituent描述"
            )
        ),
        exSpecialAttack = listOf(
            NameAndDesc(
                name = "月上海棠", description = "月上海棠描述"
            )
        ),
        chainAttack = listOf(
            NameAndDesc(
                name = "太平令", description = "太平令描述"
            )
        ),
        ultimate = listOf(
            NameAndDesc(
                name = "八聲甘州", description = "八聲甘州描述"
            )
        ),
        corePassive = listOf(
            NameAndDesc(
                name = "千秋歲", description = "千秋歲描述"
            )
        ),
        additionalAbility = listOf(
            NameAndDesc(
                name = "陽關三疊", description = "陽關三疊描述"
            )
        ),
    ),
    mindscapeCinema = listOf(
        NameAndDesc(
            name = "介電擊穿", description = "介電擊穿描述"
        ), NameAndDesc(
            name = "四兩撥千斤", description = "四兩撥千斤描述"
        ), NameAndDesc(
            name = "多喝熱水", description = "多喝熱水描述"
        ), NameAndDesc(
            name = "穩態電弧屏障", description = "穩態電弧屏障描述"
        ), NameAndDesc(
            name = "童心未泯", description = "童心未泯描述"
        ), NameAndDesc(
            name = "奇經八脈", description = "奇經八脈描述"
        )
    ),
    levelMaterial = AgentLevelMaterial(
        skillTen = listOf(
            LevelMaterial(
                id = 1, amount = 2467500
            ), LevelMaterial(
                id = 7, amount = 309
            )
        ), skillMax = listOf(
            LevelMaterial(
                id = 1, amount = 3705000
            ), LevelMaterial(
                id = 7, amount = 309
            )
        )
    ),
    suggestWEngines = listOf(
        RarityItem(47, 5), RarityItem(19, 4)
    ),
    suggestDrives = listOf(
        AgentDriveItem(5, 4), AgentDriveItem(1, 2), AgentDriveItem(7, 2)
    )
)
