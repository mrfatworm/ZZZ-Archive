/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.AgentSubAttribute
import utils.ZzzRarity

data class MyAgentDetailState(
    val agentDetail: MyAgentDetail = emptyMyAgentDetail,
    val planProperties: List<EquipPlanProperty> = emptyList(),
    val selectedSkinId: Int = 0,
    val showUid: Boolean = false,
    val uid: String = "",
    val isCustomImage: Boolean = false,
    val customImgUrl: String = "",
    val customImgAuthor: String = "",
    val hasBlurBackground: Boolean = true,
    val isImageEditMode: Boolean = false,
    val adjustMode: Boolean = false,
    val errorMessage: String = ""
)

val emptyMyAgentDetail =
    MyAgentDetail(
        id = 0,
        name = "",
        level = 0,
        rank = 0,
        imageUrl = "",
        factionImageUrl = "",
        rarity = ZzzRarity.RARITY_D,
        specialty = AgentSpecialty.None,
        attribute = AgentAttribute.None,
        subAttribute = AgentSubAttribute.None,
        equip = emptyList(),
        weapon = MyAgentDetailWeapon.Empty,
        properties = emptyList(),
        skills = emptyList(),
        equipPlanInfo = MyAgentDetailEquipPlan.Empty,
        skins = emptyList(),
        mindscapes = emptyList(),
        skillAwaken = MyAgentSkillAwaken.Empty
    )
