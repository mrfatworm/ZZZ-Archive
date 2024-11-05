/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.bangboo.model

import app.agent.model.AgentBasicData
import app.agent.model.LevelMaterial
import app.agent.model.NameAndDesc
import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.AgentAttribute
import utils.ZzzRarity

@Serializable
data class BangbooDetailResponse(
    val id: Int,
    val name: String,
    @SerialName("is_leak") val isLeak: Boolean,
    val rarity: Int,
    val attribute: String,
    @SerialName("basic_data")
    val basicData: AgentBasicData,
    @SerialName("active_skill")
    val activeSkill: NameAndDesc,
    @SerialName("additional_ability")
    val additionalAbility: NameAndDesc,
    @SerialName("chain_attack")
    val chainAttack: NameAndDesc? = null,
    val levelMaterials: List<LevelMaterial> = bangbooLevelMaterials
) {
    fun getBangbooPortraitUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Bangboo/Portrait/$id.webp"
    }

    fun getRarity(): ZzzRarity {
        return ZzzRarity.entries.find { it.level == rarity } ?: ZzzRarity.RANK_A
    }

    fun getAttribute(): AgentAttribute {
        return AgentAttribute.entries.find { it.name.lowercase() == attribute }
            ?: AgentAttribute.None
    }
}

val bangbooLevelMaterials = listOf(
    LevelMaterial(
        id = 1, amount = 500000
    ), LevelMaterial(
        id = 16, amount = 150
    ), LevelMaterial(
        id = 50, amount = 4
    ), LevelMaterial(
        id = 51, amount = 32
    ), LevelMaterial(
        id = 52, amount = 30
    )
)

val stubBangbooDetailResponse = BangbooDetailResponse(
    id = 6,
    name = "共鳴布",
    isLeak = false,
    rarity = 5,
    attribute = "ether",
    basicData = AgentBasicData(
        hp = 4210, atk = 8057, def = 72
    ),
    activeSkill = NameAndDesc(
        name = "迷你黑洞",
        description = "招式發動時，召喚以太黑洞，持續牽引周圍敵人，並對命中的所有敵人造成以太傷害並累積以太異常積蓄"
    ),
    additionalAbility = NameAndDesc(
        "輻射效應",
        "隊伍中包含至少2名[以太屬性]角色時觸發：\\n[邦布連攜技]累積的屬性異常積蓄值提升100%/125%/150%/175%/200%。"
    ),
    chainAttack = NameAndDesc(
        "原初黑洞",
        "對目標投擲以太炸彈，造成以太傷害並累積大量以太異常積蓄值。\\n\\n傷害倍率\\n979%/1044%/1110%/1175%/1240%\\n失衡倍率\\n139%/148%/158%/167%/176%"
    )
)