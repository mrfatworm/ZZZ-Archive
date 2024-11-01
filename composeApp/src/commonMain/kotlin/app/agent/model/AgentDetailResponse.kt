/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import utils.AgentAttackType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.belobog_heavy_industries
import zzzarchive.composeapp.generated.resources.criminal_investigation_special_response_team
import zzzarchive.composeapp.generated.resources.gentle_house
import zzzarchive.composeapp.generated.resources.obol_squad
import zzzarchive.composeapp.generated.resources.section_6
import zzzarchive.composeapp.generated.resources.sons_of_calydon
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.victoria_housekeeping

@Serializable
data class AgentDetailResponse(
    val id: Int,
    val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("is_leak") val isLeak: Boolean,
    val rarity: Int,
    val specialty: String,
    val attribute: String,
    @SerialName("attack_type") val attackType: String,
    @SerialName("faction_id") val factionId: Int,
    @SerialName("agent_background") val agentBackground: String,
    @SerialName("basic_data") val basicData: AgentBasicData,
    val skills: AgentSkill,
    @SerialName("mindscape_cinema") val mindscapeCinema: List<NameAndDesc>,
    @SerialName("level_material") val levelMaterial: AgentLevelMaterial,
    @SerialName("suggest_w_engines") val suggestWEngines: List<RarityItem>,
    @SerialName("suggest_drives") val suggestDrives: List<DriveItem>,
) {
    fun getAgentPortraitImageUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Portrait/$id.webp"
    }

    fun getFactionIconUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Faction/Icon/$factionId.webp"
    }

    fun getRarity(): ZzzRarity {
        return ZzzRarity.entries.find { it.level == rarity } ?: ZzzRarity.RANK_A
    }

    fun getAttribute(): AgentAttribute {
        return AgentAttribute.entries.find { it.name.lowercase().lowercase() == attribute }
            ?: AgentAttribute.None
    }

    fun getSpecialty(): AgentSpecialty {
        return AgentSpecialty.entries.find { it.name.lowercase() == specialty }
            ?: AgentSpecialty.None
    }

    fun getAttackType(): AgentAttackType {
        return AgentAttackType.entries.find { it.name.lowercase() == attackType }
            ?: AgentAttackType.None
    }

    fun getFactionNameRes(): StringResource {
        return when (factionId) {
            1 -> Res.string.gentle_house
            2 -> Res.string.victoria_housekeeping
            3 -> Res.string.belobog_heavy_industries
            4 -> Res.string.obol_squad
            5 -> Res.string.section_6
            6 -> Res.string.criminal_investigation_special_response_team
            7 -> Res.string.sons_of_calydon
            else -> Res.string.unknown
        }
    }

    fun getHpAtkDef(): String {
        return "${basicData.hp} / ${basicData.atk} / ${basicData.def}"
    }
}

@Serializable
data class AgentBasicData(
    val hp: Int,
    val atk: Int,
    val def: Int,
    @SerialName("core_skill_enhancements") val nameAndValues: List<NameAndValue> = emptyList()
)

@Serializable
data class NameAndValue(
    val name: String, val value: String
)

@Serializable
data class AgentLevelMaterial(
    @SerialName("skill_ten") val skillTen: List<LevelMaterial>,
    @SerialName("skill_max") val skillMax: List<LevelMaterial>
)

@Serializable
data class LevelMaterial(
    val id: Int, val amount: Int
) {
    fun getAmountText(): String {
        return amount.toString()
    }

    fun getProfileUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Material/$id.webp"
    }
}

@Serializable
data class RarityItem(
    val id: Int, val rarity: Int
) {
    fun getWEngineIconUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/W-Engine/Image/$id.webp"
    }

    fun getRarity(): ZzzRarity {
        return ZzzRarity.entries.find { it.level == rarity } ?: ZzzRarity.RANK_A
    }
}

@Serializable
data class DriveItem(
    val id: Int, val suit: Int
) {
    fun getDriveIconUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Drive/$id.webp"
    }

    fun getSuitString(): String {
        return suit.toString()
    }
}

@Serializable
data class AgentSkill(
    @SerialName("basic_attack") val basicAttack: List<NameAndDesc>,
    val dodge: List<NameAndDesc>,
    @SerialName("dash_attack") val dashAttack: List<NameAndDesc>,
    @SerialName("dodge_counter") val dodgeCounter: List<NameAndDesc>,
    @SerialName("quick_assist") val quickAssist: List<NameAndDesc>,
    @SerialName("defensive_assist") val defensiveAssist: List<NameAndDesc>,
    @SerialName("assist_follow_up") val assistFollowUp: List<NameAndDesc>,
    @SerialName("special_attack") val specialAttack: List<NameAndDesc>,
    @SerialName("ex_special_attack") val exSpecialAttack: List<NameAndDesc>,
    @SerialName("chain_attack") val chainAttack: List<NameAndDesc>,
    val ultimate: List<NameAndDesc>,
    @SerialName("core_passive") val corePassive: List<NameAndDesc>,
    @SerialName("additional_ability") val additionalAbility: List<NameAndDesc>
)

@Serializable
data class NameAndDesc(
    val name: String, val description: String
)

val stubAgentDetailResponse = AgentDetailResponse(
    id = 20,
    name = "青衣",
    fullName = "青衣",
    isLeak = false,
    rarity = 5,
    specialty = "stun",
    attribute = "electric",
    attackType = "strike",
    factionId = 6, agentBackground = "青衣背景",
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
        DriveItem(5, 4), DriveItem(1, 2), DriveItem(7, 2)
    )
)