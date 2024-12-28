/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.model

import feature.agent.model.AgentBasicData
import feature.agent.model.LevelMaterial
import feature.agent.model.NameAndDesc
import utils.AgentAttribute
import utils.ZzzRarity

data class BangbooDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: ZzzRarity,
    val attribute: AgentAttribute,
    val basicData: AgentBasicData,
    val activeSkill: NameAndDesc,
    val additionalAbility: NameAndDesc,
    val chainAttack: NameAndDesc?,
    val levelMaterials: List<LevelMaterial>
)

val stubBangbooDetail = BangbooDetail(
    id = 6,
    name = "共鳴布",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Bangboo/Profile/6.webp",
    isLeak = false,
    rarity = ZzzRarity.RARITY_S,
    attribute = AgentAttribute.Ether,
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
    ),
    levelMaterials = bangbooLevelMaterials
)
